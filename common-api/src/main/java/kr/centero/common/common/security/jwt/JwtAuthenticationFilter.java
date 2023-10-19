package kr.centero.common.common.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.common.client.auth.domain.model.UserToken;
import kr.centero.common.client.auth.mapper.UserTokenMapper;
import kr.centero.common.client.auth.service.CenteroUserDetailsService;
import kr.centero.core.common.util.JsonUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;


/**
 * JwtAuthenticationFilter:
 * This class is used to authenticate the user by checking the jwt token in the request header.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final String COMMON_AUTH_ENTRY_POINT = "/api/common/v1/auth";
    private final JwtTokenProvider jwtTokenProvider;
    private final UserTokenMapper userTokenMapper;
    private final HandlerExceptionResolver exceptionResolver;
    private final CenteroUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtTokenProvider jwtTokenProvider,
            CenteroUserDetailsService userDetailsService,
            UserTokenMapper userTokenMapper,
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
        this.userTokenMapper = userTokenMapper;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // skip jwt filter if request path is /api/common/v1/auth/** (login, signup, refresh, logout)
        log.info("[ZET]COMMON ENTRY POINT CHECK============>{}", request.getServletPath());
        if (request.getServletPath().contains(COMMON_AUTH_ENTRY_POINT)) {
            log.info("[ZET]COMMON ENTRY POINT SKIPPED===============>");
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader(JwtTokenProvider.AUTH_HEADER);
        final String accessToken;
        final String username;
        final boolean isValidToken;

        // skip jwt filter if auth header is null or not start with "Bearer "
        // this means that the request is not authenticated and would be handled by the AuthenticationEntryPoint
        if (authHeader == null || !authHeader.startsWith(JwtTokenProvider.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        // token validation, authentication, clean up
        try {
            accessToken = authHeader.substring(7);
            username = jwtTokenProvider.extractUsername(accessToken);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // check if the incoming token is valid and the same token exists in the database
                // because the user may have logged out and the token is deleted from the database
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UserToken userToken = userTokenMapper.findByUsername(username);
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

}
