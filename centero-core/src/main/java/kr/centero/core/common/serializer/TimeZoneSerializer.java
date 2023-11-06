package kr.centero.core.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import kr.centero.core.common.interceptor.timezone.LocaleTimeZoneContextHolder;
import kr.centero.core.common.model.CenteroUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRulesException;
import java.util.TimeZone;

/**
 * LocalDateTime을 SecurityContextHolder에 저장된 사용자 TimeZone을 가져와서
 * 해당 TimeZone의 시간대로 변경한다.
 */
public class TimeZoneSerializer extends JsonSerializer<LocalDateTime> {

    /**
     * 
     * @param value UTC 시간값을 갖고 있는 LocalDateTime형식의 값.
     * @param gen Generator used to output resulting Json content
     * @param serializers Provider that can be used to get serializers for
     *   serializing Objects value contains, if any.
     * @throws IOException
     */
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        // [01] SecurityContextHolder 에서 사용자 Timezone을 가져온다.
        TimeZone timeZone = LocaleTimeZoneContextHolder.getContext().getTimeZone();

        // [02] LocalDateTime 을 zoneId 시간대 LocalDateTime으로 변경
        LocalDateTime localDateTime = convertUtcToTimezone(value, timeZone.toZoneId());
        
        // [03] 출력 포맷 지정
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String fmtDateTime = dateTimeFormatter.format(localDateTime);

        // [03] Json 생성
        gen.writeString( fmtDateTime );
    }

    /**
     * UTC LocalDateTime 형식을 timeZoneId의 시간으로 변환한다.
     * @param dt
     * @param timeZoneId
     * @return
     */
    public static LocalDateTime convertUtcToTimezone(LocalDateTime dt, ZoneId timeZoneId) {
        // UTC 타임존으로부터 ZonedDateTime 생성
        ZonedDateTime utcZonedDateTime = dt.atZone(getZoneId("UTC"));

        // ZonedDateTime을 Asia/Seoul 타임존으로 변환
        ZonedDateTime zoneDateTime = utcZonedDateTime.withZoneSameInstant(timeZoneId);

        // LocalDateTime으로 변환
        LocalDateTime zoneLocalDateTime = zoneDateTime.toLocalDateTime();

        return zoneLocalDateTime;
    }

    /**
     * ZoneId 반환 
     * @param zone : UTC, Asia/Seoul 등
     * @return ZoneId 반환, zone이 잘못된 경우 기본 Asia/Seoul ZoneId 반환
     */
    public static ZoneId getZoneId(String zone)
    {
        try{
            return ZoneId.of(zone);
        }
        catch (ZoneRulesException e)
        {// if the zone ID is a region ID that cannot be found

        }
        catch (DateTimeException e)
        {// if the zone ID has an invalid format

        }

        // 기본 TimeZoneId
        return ZoneId.of("Asia/Seoul");
    }
}
