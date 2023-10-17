package kr.centero.common.api.common.util;

import jakarta.servlet.http.HttpServletRequest;

/**
 * IpAddressUtil:
 * This class is used to get client IP address.
 */
public class IpAddressUtil {
    private IpAddressUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getClientIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

}
