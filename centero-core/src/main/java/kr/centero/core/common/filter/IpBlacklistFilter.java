package kr.centero.core.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.core.auth.domain.model.BlacklistAccessLog;
import kr.centero.core.auth.domain.model.IpBlacklist;
import kr.centero.core.auth.service.IpBlacklistService;
import kr.centero.core.common.exception.ApplicationErrorCode;
import kr.centero.core.common.exception.ApplicationException;
import kr.centero.core.common.util.IpAddressUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * IpBlacklistFilter:
 * This class is used to check if the user's ip address is blacklisted.
 * If the user's ip address is blacklisted, it will throw an exception.
 */
@Component
public class IpBlacklistFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(IpBlacklistFilter.class);

    private final IpBlacklistService ipBlacklistService;

    private final HandlerExceptionResolver exceptionResolver;

    public IpBlacklistFilter(
            IpBlacklistService ipBlacklistService,
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.ipBlacklistService = ipBlacklistService;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String ipAddress = IpAddressUtil.getClientIP(request);
        log.info("ipAddress = {}", ipAddress);
        IpBlacklist blacklistedIp = ipBlacklistService.findByIpAddress(ipAddress);

        if (blacklistedIp != null) {

            BlacklistAccessLog blacklistAccessLog = BlacklistAccessLog.builder()
                    .ipAddress(ipAddress)
                    .userAgent(request.getHeader("User-Agent"))
                    .uri(request.getRequestURI())
                    .build();

            ipBlacklistService.insertBlacklistAccessLog(blacklistAccessLog);

            ApplicationException exception = new ApplicationException(ApplicationErrorCode.IP_BLACKLISTED, HttpStatus.UNAUTHORIZED);
            exceptionResolver.resolveException(request, response, null, exception);
            return;  // important to stop the filter chain
        }

        filterChain.doFilter(request, response);
    }

}
