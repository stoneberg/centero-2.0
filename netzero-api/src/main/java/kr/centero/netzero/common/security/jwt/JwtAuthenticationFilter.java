package kr.centero.netzero.common.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.core.common.exception.ApplicationErrorCode;
import kr.centero.core.common.exception.ApplicationException;
import kr.centero.core.common.util.CookieUtil;
import kr.centero.netzero.client.auth.domain.entity.CenteroUserTokenEntity;
import kr.centero.netzero.client.auth.domain.model.CenteroUserToken;
import kr.centero.netzero.client.auth.feign.UserAuthFeignClient;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final UserAuthFeignClient userAuthFeignClient;
    private final HandlerExceptionResolver exceptionResolver;

    public JwtAuthenticationFilter(
            JwtTokenProvider jwtTokenProvider,
            UserAuthFeignClient userAuthFeignClient,
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userAuthFeignClient = userAuthFeignClient;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String accessToken = null;
        String username;
        boolean isValidAccessToken;

        // First try to get the access token from cookie
        accessToken = this.getAccessTokenFromCookie(request, accessToken);
        log.info("[JWT_FILER]accessToken from cookie===============>{}", accessToken);

        // If access token is not found in cookie, try go get it from request header
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
            // if token is found in cookie, or request header, then cross-check it with storage
            CenteroUserTokenEntity centeroUserToken = userAuthFeignClient.findByAccessToken(accessToken);
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
                    this.refreshToken(request, centeroUserToken);
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
     * @param request          http request
     * @param centeroUserToken user token
     */
    private void refreshToken(HttpServletRequest request, CenteroUserTokenEntity centeroUserToken) {
        String oldAccessToken = centeroUserToken.getAccessToken();
        String refreshToken = centeroUserToken.getRefreshToken();
        String username = centeroUserToken.getUsername();
        String authorities = centeroUserToken.getRoles();
        boolean isValidRefreshToken = jwtTokenProvider.isTokenValid(refreshToken, username);
        log.info("[ZET]isValidRefreshToken==============>{}", isValidRefreshToken);

        if (!isValidRefreshToken) {
            log.info("[ZET]Refresh Token Expired==============>");
            throw new ApplicationException(ApplicationErrorCode.REFRESH_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
        }

        // refresh token is valid, generate new access token and save it and burn it as cookie
        CenteroUserToken currentTokenInfo = CenteroUserToken.builder()
                .accessToken(oldAccessToken)
                .refreshToken(refreshToken)
                .username(username)
                .roles(authorities)
                .build();

        String newAccessToken = userAuthFeignClient.refreshUserToken(currentTokenInfo); // call common auth api
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
     * @param request     http request
     * @param accessToken access token
     * @return access token
     */
    private String getAccessTokenFromCookie(HttpServletRequest request, String accessToken) {
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
