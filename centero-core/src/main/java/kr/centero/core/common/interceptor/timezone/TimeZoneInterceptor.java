package kr.centero.core.common.interceptor.timezone;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.core.common.constant.CenteroBaseConst;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.ZoneId;

/**
 * this interceptor is used to set user's timezoneId to TimeZoneContextHolder
 */
@Component
public class TimeZoneInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TimeZoneInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String zoneIdHeader = request.getHeader(CenteroBaseConst.CENTERO_REQUEST_TIMEZONE_HEADER);
        log.info("[TimeZoneInterceptor]ZoneID=====>{}", zoneIdHeader);

        // set default request timezoneId to "Asia/Seoul" if zoneIdHeader is null or blank
        if (StringUtils.isBlank(zoneIdHeader)) {
            zoneIdHeader = CenteroBaseConst.CENTERO_DEFAULT_REQUEST_TIMEZONE_ID;
        }

        ZoneId zoneId = ZoneId.of(zoneIdHeader);
        TimeZoneContextHolder.setZoneId(zoneId);

        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        TimeZoneContextHolder.clear();
    }
}
