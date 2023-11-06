package kr.centero.core.common.interceptor.timezone;

import kr.centero.core.common.resolver.CenteroLocaleContext;

/**
 * 사용자의 TimeZone, Locale을 저장할 ThredLocal 변수
 */
public class LocaleTimeZoneContextHolder {
    private static ThreadLocal<CenteroLocaleContext> localeContextThreadLocal = new ThreadLocal<>();

    public static void setContext(CenteroLocaleContext context) {
        localeContextThreadLocal.set(context);
    }

    public static CenteroLocaleContext getContext() {
        return localeContextThreadLocal.get();
    }

    public static void clearContext() {
        localeContextThreadLocal.remove();
    }
}
