package kr.centero.common.common.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.common.client.auth.domain.model.CenteroUserToken;
import kr.centero.common.client.auth.mapper.UserTokenMapper;
import kr.centero.common.client.auth.service.RefreshTokenService;
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
    private final UserTokenMapper userTokenMapper;
    private final RefreshTokenService refreshTokenService;
    private final HandlerExceptionResolver exceptionResolver;

    public JwtAuthenticationFilter(
            JwtTokenProvider jwtTokenProvider,
            UserTokenMapper userTokenMapper,
            RefreshTokenService refreshTokenService,
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userTokenMapper = userTokenMapper;
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

        String accessToken = null;
        String username;
        boolean isValidAccessToken;

        // First try to get the access token from cookie
        accessToken = this.getAccessToken(request, accessToken);
        log.info("[ZET]accessToken from cookie===============>{}", accessToken);

        // If access token is not found in cookie, try go get it from header
        if (accessToken == null) {
            final String authHeader = request.getHeader(JwtTokenProvider.AUTH_HEADER);
            if (authHeader != null && authHeader.startsWith(JwtTokenProvider.TOKEN_PREFIX)) {
                accessToken = authHeader.substring(7);
            }
        }

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
            CenteroUserToken centeroUserToken = userTokenMapper.findByAccessToken(accessToken);

            // 1.if user token is not found, throw exception. this means that the user was logged out already
            if (centeroUserToken == null) {
                throw new ApplicationException(ApplicationErrorCode.TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
            }

            username = centeroUserToken.getUsername();
            log.info("[ZET]username==============================>{}", username);

            // 2.check if the token is valid and the user is not authenticated yet
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // 3.check if the token is valid
                isValidAccessToken = jwtTokenProvider.isTokenValid(accessToken, username);
                log.info("[ZET]isValidAccessToken===============>{}", isValidAccessToken);

                if (isValidAccessToken) {
                    // 4.if the token is valid, create authentication object and set it in SecurityContextHolder
                    this.authenticateUser(request, centeroUserToken);
                } else {
                    // 5. if access token expired, create new access token and save it to database and cookie using refresh token
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
    private void refreshToken(HttpServletRequest request, HttpServletResponse response, CenteroUserToken centeroUserToken) {
        String refreshToken = centeroUserToken.getRefreshToken();
        String username = centeroUserToken.getUsername();
        List<String> roles = Arrays.asList(centeroUserToken.getRoles().split(","));
        boolean isValidRefreshToken = jwtTokenProvider.isTokenValid(refreshToken, username);
        log.info("[ZET]isValidRefreshToken==============>{}", isValidRefreshToken);

        if (!isValidRefreshToken) {
            throw new ApplicationException(ApplicationErrorCode.REFRESH_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
        }

        // if refresh token is valid, generate new access token and save it to database and cookie
        refreshTokenService.issueNewUserToken(refreshToken, username, roles, response);

        // if refresh token is valid, authenticate user
        this.authenticateUser(request, centeroUserToken);
    }

    /**
     * Authenticate user
     *
     * @param request          http request
     * @param centeroUserToken user token
     */
    private void authenticateUser(HttpServletRequest request, CenteroUserToken centeroUserToken) {
        // create authentication object and set it in SecurityContextHolder
        UserDetails userDetails = this.createUserDetails(centeroUserToken);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    /**
     * Get access token from cookie
     *
     * @param request     http request
     * @param accessToken access token
     * @return access token
     */
    private String getAccessToken(HttpServletRequest request, String accessToken) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CookieUtil.ACCESS_TOKEN_COOKIE.equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                    break;
                }
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
    private UserDetails createUserDetails(CenteroUserToken centeroUserToken) {
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
