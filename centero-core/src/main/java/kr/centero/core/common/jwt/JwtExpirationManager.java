package kr.centero.core.common.jwt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JwtExpirationManager:
 * This class is used to calculate the jwt token expiration time.
 * It will calculate the expiration time based on the duration value in the application.yml file.
 * m: minutes, h: hours, d: days, w: weeks
 * example: 1m, 1h, 1d, 1w
 */
@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtExpirationManager {

    @Value("${token.expiration.access}")
    private String jwtExpiration;

    @Value("${token.expiration.refresh}")
    private String refreshExpiration;

    private static final long ONE_MINUTE_IN_MILLIS = 60000;
    private static final long ONE_HOUR_IN_MILLIS = 60 * ONE_MINUTE_IN_MILLIS;
    private static final long ONE_DAY_IN_MILLIS = 24 * ONE_HOUR_IN_MILLIS;
    private static final long ONE_WEEK_IN_MILLIS = 7 * ONE_DAY_IN_MILLIS;

    public long getAccessTokenExpirationMillis() {
        long durationMillis = calculateExpiryTime(jwtExpiration);
        return (durationMillis == 0) ? getDefaultAccessExpiryTime() : durationMillis;
    }

    public long getRefreshTokenExpirationMillis() {
        long durationMillis = calculateExpiryTime(refreshExpiration);
        return (durationMillis == 0) ? getDefaultRefreshExpiryTime() : durationMillis;
    }

    private long calculateExpiryTime(String duration) {
        if (duration == null || duration.trim().isEmpty()) {
            return 0;
        }

        duration = duration.toLowerCase().trim();

        long currentTime = System.currentTimeMillis();
        long durationInMillis;

        try {
            int durationVal = Integer.parseInt(duration.substring(0, duration.length() - 1));
            char unit = duration.charAt(duration.length() - 1);
            durationInMillis = switch (unit) {
                case 'm' -> durationVal * ONE_MINUTE_IN_MILLIS;
                case 'h' -> durationVal * ONE_HOUR_IN_MILLIS;
                case 'd' -> durationVal * ONE_DAY_IN_MILLIS;
                case 'w' -> durationVal * ONE_WEEK_IN_MILLIS;
                default -> throw new IllegalArgumentException("Unknown duration: " + duration);
            };
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid duration format: " + duration, ex);
        }

        return currentTime + durationInMillis;
    }

    private long getDefaultAccessExpiryTime() {
        return System.currentTimeMillis() + ONE_DAY_IN_MILLIS;
    }

    private long getDefaultRefreshExpiryTime() {
        return System.currentTimeMillis() + ONE_WEEK_IN_MILLIS;
    }

}
