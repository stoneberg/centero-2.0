package kr.centero.netzero.common.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.core.common.exception.ApplicationErrorCode;
import kr.centero.core.common.exception.ApplicationException;
import kr.centero.core.common.util.CookieUtil;
import kr.centero.netzero.client.auth.mapper.UserTokenMapper;
import kr.centero.netzero.common.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kr.centero.netzero.common.security.jwt.JwtTokenProvider.AUTH_HEADER;
import static kr.centero.netzero.common.security.jwt.JwtTokenProvider.TOKEN_PREFIX;


/**
 * CustomLogoutHandler:
 * This class is used to handle logout requests.
 * It will delete the user's accessToken from the database and delete the refreshToken cookie.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {
    private final UserTokenMapper userTokenMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieUtil cookieUtil;

    @Transactional
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
            return;
        }

        final String accessToken = authHeader.substring(7);

        if (!StringUtils.isEmpty(accessToken)) {
            // delete user's accessToken
            String username = jwtTokenProvider.extractUsername(accessToken);
            userTokenMapper.deleteByUsername(username);
            // delete access, refresh token cookie
            cookieUtil.cleanUpCookie(CookieUtil.ACCESS_TOKEN_COOKIE, response);
        }

        // check if the refreshToken cookie exists
        boolean accessTokenFound = cookieUtil.doesCookieExist(request, CookieUtil.ACCESS_TOKEN_COOKIE);

        // if accessToken cookie or refreshToken cookie not found, it means that user already logged out
        if (!accessTokenFound) {
            throw new ApplicationException(ApplicationErrorCode.TOKEN_EXPIRED, HttpStatus.FORBIDDEN);
        }

    }

}
