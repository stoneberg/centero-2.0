package kr.centero.common.common.security;

import jakarta.servlet.http.HttpServletRequest;
import kr.centero.common.client.auth.service.CenteroUserDetailsService;
import kr.centero.common.client.auth.service.LoginLogService;
import kr.centero.core.common.exception.ApiErrorCode;
import kr.centero.core.common.exception.ApiException;
import kr.centero.core.common.model.LoginLog;
import kr.centero.core.common.util.IpAddressUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * AuthenticationProvider:
 * Authenticate the user by checking the credentials against some source of data.
 * In this case, the source of data is the database.
 * Make logs of login success or failure base on the result count of authentication.
 */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final int MAX_FAILURE_COUNT = 5;
    private static final int RESET_FAILURE_COUNT = 0;
    private final CenteroUserDetailsService userDetailsService;
    private final HttpServletRequest httpServletRequest;
    private final LoginLogService loginLogService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String presentedPassword = (String) authentication.getCredentials();

        if (StringUtils.isEmpty(username)) {
            throw new ApiException(ApiErrorCode.BAD_CREDENTIALS, HttpStatus.UNAUTHORIZED);
        }

        Integer count = loginLogService.findRecentFailureCount(username);
        int recentFailureCount = (count != null) ? count : 0;
        String clientIP = IpAddressUtil.getClientIP(httpServletRequest);

        if (recentFailureCount >= MAX_FAILURE_COUNT) {
            throw new ApiException(ApiErrorCode.LOGIN_FAILURE_EXCEED_MAX_COUNT, HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            // make log of login failure
            this.logAuthentication(username, false, clientIP, recentFailureCount + 1);
            throw new ApiException(ApiErrorCode.LOGIN_FAILURE, String.valueOf(recentFailureCount + 1), HttpStatus.UNAUTHORIZED);
        }

        // make log of login success and reset failure count
        this.logAuthentication(username, true, clientIP, RESET_FAILURE_COUNT);

        return this.createSuccessfulAuthentication(authentication, userDetails);
    }

    private void logAuthentication(String username, boolean loginSuccess, String clientIP, int recentFailureCount) {
        LoginLog loginLog = this.getLoginLog(username, loginSuccess, clientIP, recentFailureCount);
        loginLogService.insertLoginLog(loginLog);
    }

    private Authentication createSuccessfulAuthentication(final Authentication authentication, final UserDetails user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
        token.setDetails(authentication.getDetails());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private LoginLog getLoginLog(String username, boolean loginSuccess, String clientIP, int recentFailureCount) {
        return LoginLog.builder()
                .username(username)
                .loginSuccess(loginSuccess)
                .ipAddress(clientIP)
                .loginTime(LocalDateTime.now())
                .failureCount(recentFailureCount)
                .build();
    }
}

