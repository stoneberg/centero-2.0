package kr.centero.common.test;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ZoneIdDisplayTest {

    @Test
    void getAllOfZoneIdList() {
        Instant instant = Instant.now();

        Map<String, String> result = ZoneId.getAvailableZoneIds()
                .stream()
                .map(ZoneId::of)
                .map(zoneId -> new SimpleEntry<>(zoneId.toString(), instant.atZone(zoneId)
                        .getOffset()
                        .getId()
                        .replaceAll("Z", "+00:00")))
                .sorted(Map.Entry.<String, String>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        SimpleEntry::getKey,
                        SimpleEntry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));

        result.forEach((k, v) -> System.out.printf(String.format("%35s %s %n", k, v)));
        System.out.println("\nNumber of Zone IDs " + result.size());

        assertTrue(result.size() > 600, "The number of Zone IDs should be more than 600");

    }

    @Test
    void testGetUtcTime() {
        String zoneIdString = "Asia/Seoul";
        String localTimeString = "2023-10-31 12:00:00";
        String expectedUtcTimeString = "2023-10-31 03:00:00";

        String actualUtcTimeString = getUtcTime(zoneIdString, localTimeString);

        assertEquals(expectedUtcTimeString, actualUtcTimeString);
    }

    @Test
    void testGetLocalTime() {
        String zoneIdString = "Asia/Seoul";
        String utcTimeString = "2023-10-31 03:00:00";
        String expectedLocalTimeString = "2023-10-31 12:00:00";

        String actualLocalTimeString = getLocalTime(zoneIdString, utcTimeString);

        assertEquals(expectedLocalTimeString, actualLocalTimeString);
    }


    /**
     * convert localTime to UTC time by zoneId
     *
     * @param zoneIdString Asia/Seoul
     * @param localTimeString 2021-07-01 00:00:00
     * @return UTC time
     */
    public String getUtcTime(String zoneIdString, String localTimeString) {
        // 로컬 시간 문자열을 LocalDateTime 객체로 파싱합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(localTimeString, formatter);

        // ZoneId 객체를 생성하고, 로컬 시간을 해당 시간대의 ZonedDateTime 객체로 변환합니다.
        ZoneId zoneId = ZoneId.of(zoneIdString);
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);

        // ZonedDateTime 객체를 UTC 시간대로 변환합니다.
        ZonedDateTime utcZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));

        // UTC 시간을 문자열로 포맷팅하고 반환합니다.
        return utcZonedDateTime.format(formatter);
    }

    /**
     * convert UTC time to localTime by zoneId
     *
     * @param zoneIdString Asia/Seoul
     * @param utcTimeString 2021-07-01 00:00:00
     * @return localTime
     */
    public static String getLocalTime(String zoneIdString, String utcTimeString) {
        // UTC 시간 문자열을 LocalDateTime 객체로 파싱합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime utcLocalDateTime = LocalDateTime.parse(utcTimeString, formatter);

        // ZoneId 객체를 생성하고, UTC 시간을 해당 시간대의 ZonedDateTime 객체로 변환합니다.
        ZoneId zoneId = ZoneId.of(zoneIdString);
        ZonedDateTime utcZonedDateTime = utcLocalDateTime.atZone(ZoneId.of("UTC"));

        // ZonedDateTime 객체를 목표 시간대로 변환합니다.
        ZonedDateTime targetZonedDateTime = utcZonedDateTime.withZoneSameInstant(zoneId);

        // 목표 시간대의 시간을 문자열로 포맷팅하고 반환합니다.
        return targetZonedDateTime.format(formatter);
    }

}
