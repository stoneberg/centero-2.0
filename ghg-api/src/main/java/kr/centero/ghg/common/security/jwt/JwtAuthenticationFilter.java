package kr.centero.ghg.common.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.core.common.exception.ApplicationErrorCode;
import kr.centero.core.common.exception.ApplicationException;
import kr.centero.ghg.client.auth.domain.model.UserToken;
import kr.centero.ghg.client.auth.mapper.UserTokenMapper;
import org.apache.commons.lang3.ObjectUtils;
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
    private static final String GHG_AUTH_ENTRY_POINT = "/api/ghg/v1/auth";
    private final JwtTokenProvider jwtTokenProvider;
    private final UserTokenMapper userTokenMapper;
    private final HandlerExceptionResolver exceptionResolver;

    public JwtAuthenticationFilter(
            JwtTokenProvider jwtTokenProvider,
            UserTokenMapper userTokenMapper,
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userTokenMapper = userTokenMapper;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // skip jwt filter if request path is /api/ghg/v1/auth/** (login, signup, refresh, logout)
        if (request.getServletPath().contains(GHG_AUTH_ENTRY_POINT)) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = null;
        String username;
        boolean isValidToken;

        // First try to get the access token from cookie
        accessToken = this.getAccessToken(request, accessToken);

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

        // token validation, authentication, clean up
        try {
            username = jwtTokenProvider.extractUsername(accessToken);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // check if the incoming token is valid and the same token exists in the database
                // because the user may have logged out then the token is deleted from the database
                UserToken userToken = userTokenMapper.findByUsername(username); // @todo : redis 에서 조회하도록 변경
                System.out.println("[USER ROLE]userToken = " + userToken);
                // if (userToken == null) throw new ApplicationException(ApplicationErrorCode.TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
                log.info("[ZET]userToken===============>{}", userToken);
                UserDetails userDetails = this.createUserDetails(userToken);
                log.info("[ZET]userDetails=============>{}", userDetails);

                isValidToken = jwtTokenProvider.isTokenValid(accessToken, userDetails)
                        && !ObjectUtils.isEmpty(userToken);

                if (isValidToken) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            // delegate the exception to the global exception handler (ControllerAdvice)
            exceptionResolver.resolveException(request, response, null, e);
        }
    }

    private String getAccessToken(HttpServletRequest request, String accessToken) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessToken" .equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                    break;
                }
            }
        }
        return accessToken;
    }

    private UserDetails createUserDetails(UserToken userToken) {
        try {
            List<SimpleGrantedAuthority> authorities = Arrays.stream(userToken.getRoles().split(","))
                    .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                    .toList();
            return new User(userToken.getUsername(), "", authorities);
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrorCode.TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
        }
    }

}
