package kr.centero.common.common.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.common.client.auth.service.UserTokenRedisService;
import kr.centero.core.common.exception.ApiErrorCode;
import kr.centero.core.common.exception.ApiException;
import kr.centero.core.common.jwt.JwtTokenProvider;
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
        this.deleteJwtLoginSession(request, response);
    }

    /**
     * Delete jwt login session
     * delete access token in redis and delete access cookie
     *
     * @param request  http request
     * @param response http response
     */
    public void deleteJwtLoginSession(HttpServletRequest request, HttpServletResponse response) {
        final String accessToken = getAccessTokenFromRequest(request);
        log.info("[LOGOUT_HANDLER]accessToken===============>{}", accessToken);
        if (!StringUtils.isEmpty(accessToken)) {
            // delete access token in redis
            userTokenRedisService.deleteByAccessToken(accessToken);
            // delete access cookie
            cookieUtil.deleteAccessCookie(response);
        } else {
             throw new ApiException(ApiErrorCode.TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
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
