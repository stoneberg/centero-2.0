package kr.centero.core.common.util;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class TimeZoneUtil {

    /**
     * DB에서 조회된 UTC 시간을 사용자의 현지 시간으로 변환
     *
     * @param zoneIdString Asia/Seoul
     * @param utcDateTime 2021-07-01 00:00:00
     * @return localTime
     */
    public static LocalDateTime convertUtcToLocalTime(String zoneIdString, LocalDateTime utcDateTime) {
        // UTC LocalDateTime을 ZonedDateTime으로 변환
        ZonedDateTime utcZonedDateTime = utcDateTime.atZone(ZoneId.of("UTC"));

        // UTC ZonedDateTime을 지역 시간대로 변환
        ZoneId zoneId = ZoneId.of(zoneIdString);
        ZonedDateTime localZonedDateTime = utcZonedDateTime.withZoneSameInstant(zoneId);

        // ZonedDateTime을 LocalDateTime으로 변환
        return localZonedDateTime.toLocalDateTime();
    }

}
