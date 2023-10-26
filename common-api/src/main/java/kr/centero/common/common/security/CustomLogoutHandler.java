package kr.centero.common.common.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.common.client.auth.service.UserTokenRedisService;
import kr.centero.common.common.security.jwt.JwtTokenProvider;
import kr.centero.core.common.exception.ApplicationErrorCode;
import kr.centero.core.common.exception.ApplicationException;
import kr.centero.core.common.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * CustomLogoutHandler:
 * This class is used to handle logout requests.
 * It will delete the user's accessToken from the database and delete the refreshToken cookie.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {
    private final UserTokenRedisService userTokenRedisService;
    private final CookieUtil cookieUtil;

    @Transactional
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String accessToken = getAccessTokenFromRequest(request);

        if (!StringUtils.isEmpty(accessToken)) {
            // delete access token in redis
            userTokenRedisService.deleteByAccessToken(accessToken);
            // delete access cookie
            cookieUtil.deleteAccessCookie(response);
        }

        // check if the refreshToken cookie exists
        boolean accessCookieExist = cookieUtil.doesAccessCookieExist(request);
        log.info("[LOGOUT]accessCookieExist===============>{}", accessCookieExist);

        // if accessToken cookie or refreshToken cookie not found, it means that user already logged out
        if (!accessCookieExist) {
            throw new ApplicationException(ApplicationErrorCode.TOKEN_EXPIRED, HttpStatus.FORBIDDEN);
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

}
