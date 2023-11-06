package kr.centero.core.common.interceptor.timezone;

import java.time.ZoneId;

/**
 * this class is used to store user's timezoneId
 */
public class TimeZoneContextHolder {
    private static final ThreadLocal<ZoneId> contextHolder = new ThreadLocal<>();

    private TimeZoneContextHolder() {
    }

    public static void setZoneId(ZoneId zoneId) {
        contextHolder.set(zoneId);
    }

    public static ZoneId getZoneId() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }
}
