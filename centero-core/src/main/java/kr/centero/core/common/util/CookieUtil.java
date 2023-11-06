package kr.centero.core.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

/**
 * CookieUtil:
 * This class is used to create, delete, and get cookie.
 */
@Slf4j
@Component
public class CookieUtil {
    private static final String COOKIE_HEADER = "Set-Cookie";
    public static final String ACCESS_TOKEN_COOKIE = "accessToken";

    @Value("${cookie.access-token.duration}")
    private String cookieDuration;

    @Value("${cookie.access-token.domain}")
    private String cookieDomain;

    @Value("${cookie.access-token.secure}")
    private Boolean secure;

    @Value("${cookie.access-token.same-site}")
    private String sameSite;


    /**
     * write access cookie
     *
     * @param value    cookie value
     * @param response HttpServletResponse
     */
    public void writeAccessCookie(String value, HttpServletResponse response) {
        ResponseCookie cookie = this.burnCookie(ACCESS_TOKEN_COOKIE, value, cookieDuration);
        response.addHeader(COOKIE_HEADER, cookie.toString());
    }

    /**
     * set cookie with default duration of 1 day
     *
     * @param name     cookie name
     * @param value    cookie value
     * @param duration cookie duration (ex. 1d, 1h, 1m) [max-age]
     * @return created cookie
     */
    public ResponseCookie burnCookie(String name, String value, String duration) {
        int expiry = this.getExpiry(duration);

        return ResponseCookie.from(name, value)
                .path("/")
                .httpOnly(true)
                .maxAge(expiry)
                .domain(cookieDomain)
                .secure(secure) // if true, https only
                .sameSite(sameSite) // this is cross-site cookie option (None, Lax, Strict). if "None", above secure option must be true
                .build();
    }

    private int getExpiry(String duration) {
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
        return expiry;
    }

    /**
     * check if cookie exists by specified name
     *
     * @param request    HttpServletRequest
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
        log.info("[LOGOUT]CookieName=======>{}", name);
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setDomain(cookieDomain);
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

    /**
     * delete access cookie
     *
     * @param response HttpServletResponse
     */
    public void deleteAccessCookie(HttpServletResponse response) {
        Cookie cookie = this.deleteCookie(ACCESS_TOKEN_COOKIE);
        response.addCookie(cookie);
    }

}
