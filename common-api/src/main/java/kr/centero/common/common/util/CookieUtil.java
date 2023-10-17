package kr.centero.common.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * CookieUtil:
 * This class is used to create, delete, and get cookie.
 */
public class CookieUtil {
    public static final String REFRESH_TOKEN_COOKIE = "refreshToken";

    private CookieUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * create cookie with default duration of 1 day
     *
     * @param name     cookie name
     * @param value    cookie value
     * @param duration cookie duration (ex. 1d, 1h, 1m)
     * @return created cookie
     */
    public static Cookie burnCookie(String name, String value, String duration) {
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
        // cookie.setSecure(true); // https에서만 쿠키 사용 가능
        return cookie;
    }

    /**
     * create cookie
     *
     * @param name     cookie name
     * @param value    cookie value
     * @param maxAge   cookie max age
     * @param response HttpServletResponse
     */
    public static void createCookie(String name, String value, String maxAge, HttpServletResponse response) {
        Cookie cookie = CookieUtil.burnCookie(name, value, maxAge);
        response.addCookie(cookie);
    }

    /**
     * get cookie value by name
     *
     * @param request HttpServletRequest
     * @return cookie value
     */
    public static String getCookieValueByName(HttpServletRequest request, String name) {
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
    public static boolean doesCookieExist(HttpServletRequest request, String cookieName) {
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
    public static Cookie deleteCookie(String name) {
        Cookie cookie = new Cookie(name, "");
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
    public static void cleanUpCookie(String name, HttpServletResponse response) {
        Cookie cookie = CookieUtil.deleteCookie(name);
        response.addCookie(cookie);
    }

}
