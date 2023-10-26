package kr.centero.common.common.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.common.client.auth.domain.entity.CenteroUserTokenEntity;
import kr.centero.common.client.auth.service.RefreshTokenService;
import kr.centero.common.client.auth.service.UserTokenRedisService;
import kr.centero.core.common.exception.ApplicationErrorCode;
import kr.centero.core.common.exception.ApplicationException;
import kr.centero.core.common.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * JwtAuthenticationFilter:
 * This class is used to authenticate the user by checking the jwt token in the request header.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final String ROLE_PREFIX = "ROLE_";
    private static final String COMMON_AUTH_ENTRY_POINT = "/api/common/v1/auth";
    private final JwtTokenProvider jwtTokenProvider;
    private final UserTokenRedisService userTokenRedisService;
    private final RefreshTokenService refreshTokenService;
    private final HandlerExceptionResolver exceptionResolver;

    public JwtAuthenticationFilter(
            JwtTokenProvider jwtTokenProvider,
            UserTokenRedisService userTokenRedisService,
            RefreshTokenService refreshTokenService,
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userTokenRedisService = userTokenRedisService;
        this.refreshTokenService = refreshTokenService;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // skip jwt filter if request path is /api/common/v1/auth/** (login, signup, refresh, logout)
        if (request.getServletPath().contains(COMMON_AUTH_ENTRY_POINT)) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken;
        String username;
        boolean isValidAccessToken;

        // First try to get the access token from cookie or header
        accessToken = this.getAccessTokenFromRequest(request);
        log.info("[COMMON_JWT_FILER]accessToken from request===============>{}", accessToken);

        // skip jwt filter if auth header is null or not start with "Bearer "
        // this means that the request is not authenticated and would be handled by the AuthenticationEntryPoint
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // User auth validation: forged token, expired token is thrown as exception by library
        // we don't want to handle auth service logic in catch block depending on the exception type
        // we have to get username from the storage at first to check if the user is authenticated or not
        try {

            log.info("[ZET]accessToken==============================>{}", accessToken);
            // if token is found in cookie, or request header, then cross-check it with storage
            CenteroUserTokenEntity centeroUserToken = userTokenRedisService.findByAccessToken(accessToken);
            log.info("[ZET]centeroUserToken=========================>{}", centeroUserToken);

            // 1.if token is not found in storage, throw exception. this means that the user was logged out already
            if (centeroUserToken == null) {
                throw new ApplicationException(ApplicationErrorCode.TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
            }

            username = centeroUserToken.getUsername();
            log.info("[ZET]username==============================>{}", username);

            // 2.check if the token is valid and the user is not authenticated yet
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // check if the token is valid
                isValidAccessToken = jwtTokenProvider.isTokenValid(accessToken, username);
                log.info("[ZET]isValidAccessToken===============>{}", isValidAccessToken);

                // 3.authenticate user if the token is valid or use refresh token to issue new access token
                if (isValidAccessToken) {
                    // 4.token is valid, authenticate user
                    this.authenticateUser(request, centeroUserToken);
                } else {
                    // 5.token expired, issue new access token using refresh token
                    this.refreshToken(request, response, centeroUserToken);
                }

            }

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            // delegate the exception to the global exception handler (ControllerAdvice)
            exceptionResolver.resolveException(request, response, null, e);
        }
    }

    /**
     * Create new access token and save it to database and cookie using refresh token
     *
     * @param response         http response
     * @param centeroUserToken user token
     */
    private void refreshToken(HttpServletRequest request, HttpServletResponse response, CenteroUserTokenEntity centeroUserToken) {
        String oldAccessToken = centeroUserToken.getAccessToken();
        String refreshToken = centeroUserToken.getRefreshToken();
        String username = centeroUserToken.getUsername();
        List<String> roles = Arrays.asList(centeroUserToken.getRoles().split(","));
        boolean isValidRefreshToken = jwtTokenProvider.isTokenValid(refreshToken, username);
        log.info("[ZET]isValidRefreshToken==============>{}", isValidRefreshToken);

        if (!isValidRefreshToken) {
            log.info("[ZET]Refresh Token Expired==============>");
            throw new ApplicationException(ApplicationErrorCode.REFRESH_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
        }

        // refresh token is valid, generate new access token and save it and burn it as cookie
        String newAccessToken = refreshTokenService.issueNewUserToken(oldAccessToken, refreshToken, username, roles, response);
        log.info("[ZET]New AccessToken Issued By RefreshToken==============>{}", newAccessToken);

        // refresh token is valid, authenticate user
        this.authenticateUser(request, centeroUserToken);
    }

    /**
     * Authenticate user
     *
     * @param request          http request
     * @param centeroUserToken user token
     */
    private void authenticateUser(HttpServletRequest request, CenteroUserTokenEntity centeroUserToken) {
        // create authentication object and set it in SecurityContextHolder
        UserDetails userDetails = this.createUserDetails(centeroUserToken);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    /**
     * Get access token from cookie
     *
     * @param request http request
     * @return access token
     */
    private String getAccessTokenFromRequest(HttpServletRequest request) {
        // get access token from cookie
        String accessToken = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CookieUtil.ACCESS_TOKEN_COOKIE.equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                    break;
                }
            }
        }

        // If access token is not found in cookie, try go get it from request header
        if (accessToken == null) {
            final String authHeader = request.getHeader(JwtTokenProvider.AUTH_HEADER);
            if (authHeader != null && authHeader.startsWith(JwtTokenProvider.TOKEN_PREFIX)) {
                accessToken = authHeader.substring(7);
            }
        }

        return accessToken;
    }

    /**
     * Create user details object from user token info
     *
     * @param centeroUserToken user token
     * @return user details
     */
    private UserDetails createUserDetails(CenteroUserTokenEntity centeroUserToken) {
        try {
            List<SimpleGrantedAuthority> authorities = Arrays.stream(centeroUserToken.getRoles().split(","))
                    .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                    .toList();
            return new User(centeroUserToken.getUsername(), "", authorities);
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrorCode.TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
        }
    }

}
