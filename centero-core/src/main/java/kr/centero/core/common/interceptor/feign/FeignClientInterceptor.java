package kr.centero.core.common.interceptor.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import kr.centero.core.common.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Server to Server API 호출 시, 인증이 필요한 경우
 * When making an API request, extract the accessToken from the cookie and set it in the request header
 */
@Component
@RequiredArgsConstructor
public class FeignClientInterceptor implements RequestInterceptor {
    private static final String AUTH_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String CURRENT_ACCESS_TOKEN = "accessToken";
    private static final String NEW_ACCESS_TOKEN = "newAccessToken";
    private final HttpServletRequest request;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void apply(RequestTemplate template) {
        // current access token from cookie
        String token = extractTokenFromCookie(request);

        // if token is null or token is invalid, get new access token from request attribute
        if (token == null || Boolean.FALSE.equals(jwtTokenProvider.isTokenValid(token))) {
            token = (String) request.getAttribute(NEW_ACCESS_TOKEN);
        }

        // set access token in request header
        if (token != null) {
            template.header(AUTH_HEADER, TOKEN_PREFIX + token);
        }
    }

    /**
     * Extract access token from cookie
     *
     * @param request HttpServletRequest
     * @return access token
     */
    public String extractTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (CURRENT_ACCESS_TOKEN.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
