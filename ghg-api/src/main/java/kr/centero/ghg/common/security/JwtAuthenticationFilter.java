package kr.centero.ghg.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.core.common.exception.ApiErrorCode;
import kr.centero.core.common.exception.ApiException;
import kr.centero.core.common.jwt.JwtTokenProvider;
import kr.centero.core.common.model.CenteroUserToken;
import kr.centero.core.common.model.RedisToken;
import kr.centero.core.common.payload.ApiResponse;
import kr.centero.core.common.util.CookieUtil;
import kr.centero.ghg.openapi.service.UserAuthFeignClient;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.util.ObjectUtils;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;


/**
 * GHG Server-JwtAuthenticationFilter:
 * This class is used to authenticate the user by checking the jwt token in the request header.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    protected static final String[] EXCLUDE_PATH_PATTERN = {
            "/swagger-ui/**"
    };
    private static final String NEW_ACCESS_TOKEN = "newAccessToken";
    private static final String ROLE_PREFIX = "ROLE_";
    private final JwtTokenProvider jwtTokenProvider;
    private final UserAuthFeignClient userAuthFeignClient;
    private final ObjectMapper objectMapper;
    private final CookieUtil cookieUtil;

    private final HandlerExceptionResolver exceptionResolver;

    public JwtAuthenticationFilter(
            JwtTokenProvider jwtTokenProvider,
            UserAuthFeignClient userAuthFeignClient,
            ObjectMapper objectMapper,
            CookieUtil cookieUtil,
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userAuthFeignClient = userAuthFeignClient;
        this.objectMapper = objectMapper;
        this.cookieUtil = cookieUtil;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (isWhitePath(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        boolean isValidAccessToken;
        boolean isValidRefreshToken;
        String accessToken;
        String username;
        List<String> roles;

        // First try to get the access token from cookie or header
        accessToken = this.getAccessTokenFromRequest(request);
        log.info("1.accessToken===============>{}", accessToken);

        // skip jwt filter if auth header is null or not start with "Bearer "
        // this means that the request is not authenticated and would be handled by the AuthenticationEntryPoint
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            isValidAccessToken = jwtTokenProvider.isTokenValid(accessToken);
            log.info("2.isValidAccessToken============>{}", isValidAccessToken);

            // 1.accessToken is valid, authenticate user in spring security
            if (isValidAccessToken) {
                username = jwtTokenProvider.extractUsername(accessToken);
                roles = jwtTokenProvider.extractRoles(accessToken);
                this.authenticateUser(request, username, roles);
            }

            // 2.accessToken is not valid, check if refreshToken is valid
            if (!isValidAccessToken) {
                // get refreshToken from redis
                ApiResponse<RedisToken> apiResponse = userAuthFeignClient.findByAccessToken(accessToken);
                RedisToken redisToken = objectMapper.convertValue(apiResponse.getData(), RedisToken.class);

                // if refreshToken is not found in redis, throw exception -> force user to login again
                if (ObjectUtils.isEmpty(redisToken)) {
                    throw new ApiException(ApiErrorCode.REFRESH_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
                }

                String refreshToken = redisToken.getRefreshToken();
                isValidRefreshToken = jwtTokenProvider.isTokenValid(refreshToken);
                log.info("3.isValidRefreshToken===================>{}", isValidRefreshToken);

                // if refreshToken is expired, throw exception -> force user to login again
                if (!isValidRefreshToken) {
                    throw new ApiException(ApiErrorCode.REFRESH_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
                }

                // if refreshToken is valid, generate new accessToken
                username = redisToken.getUsername();
                roles = List.of(redisToken.getRoles().split(","));

                this.refreshToken(accessToken, refreshToken, username, roles, request, response);
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
     * @param accessToken  current access token
     * @param refreshToken current refresh token
     * @param username     username
     * @param roles        user roles
     * @param request      http request
     * @param response     http response
     */
    private void refreshToken(String accessToken, String refreshToken, String username, List<String> roles, HttpServletRequest request, HttpServletResponse response) {
        // refresh token is valid, generate new access token and save it and burn it as cookie
        CenteroUserToken centeroUserToken = CenteroUserToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .username(username)
                .roles(String.join(",", roles))
                .build();

        ApiResponse<String> apiResponse = userAuthFeignClient.refresh(centeroUserToken);
        String newAccessToken = apiResponse.getData();
        log.info("4.[GHG::New Access Token=======>{}", newAccessToken);

        // set new access token in request attribute
        request.setAttribute(NEW_ACCESS_TOKEN, newAccessToken);

        // refresh token is valid, authenticate user
        this.authenticateUser(request, username, roles);

        // Create new Cookie
        cookieUtil.writeAccessCookie(newAccessToken, response);
    }

    /**
     * Authenticate user
     *
     * @param request  http request
     * @param username username
     * @param roles    user roles
     */
    private void authenticateUser(HttpServletRequest request, String username, List<String> roles) {
        // create authentication object and set it in SecurityContextHolder
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.createUserDetails(username, roles);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
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
     * @param username username
     * @param roles    user roles
     * @return user details
     */
    private UserDetails createUserDetails(String username, List<String> roles) {
        try {
            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                    .toList();
            return new User(username, StringUtils.EMPTY, authorities);
        } catch (Exception e) {
            throw new ApiException(ApiErrorCode.TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean isWhitePath(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return PatternMatchUtils.simpleMatch(EXCLUDE_PATH_PATTERN, requestURI);
    }

}
