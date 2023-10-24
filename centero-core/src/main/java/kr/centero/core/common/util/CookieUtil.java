package kr.centero.core.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * CookieUtil:
 * This class is used to create, delete, and get cookie.
 */
@Slf4j
@Component
public class CookieUtil {
    public static final String ACCESS_TOKEN_COOKIE = "access_token";
    public static final String REFRESH_TOKEN_COOKIE = "refresh_token";

    @Value("${cookie.access.duration}")
    private String accessTokenCookieDuration;

    @Value("${cookie.refresh.duration}")
    private String refreshTokenCookieDuration;

    /**
     * write cookie
     *
     * @param name     cookie name
     * @param value    cookie value
     * @param duration   cookie max age
     * @param response HttpServletResponse
     */
    public void writeCookie(String name, String value, String duration, HttpServletResponse response) {
        Cookie cookie = this.burnCookie(name, value, duration);
        response.addCookie(cookie);
    }

    /**
     * write access cookie
     *
     * @param value    cookie value
     * @param response HttpServletResponse
     */
    public void writeAccessCookie(String value, HttpServletResponse response) {
        Cookie cookie = this.burnCookie(ACCESS_TOKEN_COOKIE, value, accessTokenCookieDuration);
        // log.info("[ZET]writeAccessCookie============>{}", cookie);
        response.addCookie(cookie);
    }

    /**
     * write refresh cookie
     *
     * @param value    cookie value
     * @param response HttpServletResponse
     */
    public void writeRefreshCookie(String value, HttpServletResponse response) {
        Cookie cookie = this.burnCookie(REFRESH_TOKEN_COOKIE, value, refreshTokenCookieDuration);
        response.addCookie(cookie);
    }

    /**
     * create cookie with default duration of 1 day
     *
     * @param name     cookie name
     * @param value    cookie value
     * @param duration cookie duration (ex. 1d, 1h, 1m) [max-age]
     * @return created cookie
     */
    public Cookie burnCookie(String name, String value, String duration) {
        Cookie cookie = new Cookie(name, value);

        // duration toLowerCase
        duration = duration.toLowerCase();

        // if invalid duration unit and value, set default duration to 1 day
        if (!duration.matches("\\d+[dhm]")) {
            duration = "1d";
        }

        int expiry = 0;

        int durationVal = Integer.parseInt(duration.substring(0, duration.length() - 1));
        if (duration.endsWith("d")) {
            expiry = durationVal * 24 * 60 * 60;
        } else if (duration.endsWith("h")) {
            expiry = durationVal * 60 * 60;
        } else if (duration.endsWith("m")) {
            expiry = durationVal * 60;
        }

        cookie.setMaxAge(expiry);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setDomain("centero.kr");
        // cookie.setSecure(true); // https에서만 쿠키 사용 가능
        return cookie;
    }

    /**
     * get cookie value by name
     *
     * @param request HttpServletRequest
     * @return cookie value
     */
    public String readCookieByName(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * check if cookie exists by specified name
     *
     * @param request HttpServletRequest
     * @param cookieName cookie name
     * @return boolean
     */
    public boolean doesCookieExist(HttpServletRequest request, String cookieName) {
        boolean cookieExists = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    cookieExists = true;
                    break;
                }
            }
        }
        return cookieExists;
    }

    /**
     * delete cookie by setting maxAge to 0
     *
     * @param name cookie name
     * @return deleted cookie
     */
    public Cookie deleteCookie(String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        return cookie;
    }

    /**
     * clean up cookie from response header
     *
     * @param name     cookie name
     * @param response HttpServletResponse
     */
    public void cleanUpCookie(String name, HttpServletResponse response) {
        Cookie cookie = this.deleteCookie(name);
        response.addCookie(cookie);
    }

}
