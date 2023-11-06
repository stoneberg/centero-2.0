package kr.centero.core.common.interceptor.timezone;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.core.common.resolver.CenterLocaleContextResolver;
import kr.centero.core.common.resolver.CenteroLocaleContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Reuqest로 부터 Locale, Timezone을 조회해서 ThreadLocal 변수에 저장 또는 제거하는 인터셉터
 */
@Slf4j
@Component
public class LocaleTimeZoneInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // [01] Locale, Timezone 컨텍스트 객체 조회 (request로 부터)
        CenterLocaleContextResolver resolver = new CenterLocaleContextResolver();
        CenteroLocaleContext localeContext = resolver.resolveLocaleContext(request);

        // [02] ThreadLocal 변수에 저장
        LocaleTimeZoneContextHolder.setContext(localeContext);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

        // [02] ThreadLocal 변수 제거
        LocaleTimeZoneContextHolder.clearContext();
    }
}
