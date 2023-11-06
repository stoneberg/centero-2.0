package kr.centero.core.common.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleContextResolver;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * LocaleContextResolver는 요청의 Locale 및 TimeZone을 다루는 데 사용
 */

@Component
public class CenterLocaleContextResolver {
    public CenteroLocaleContext resolveLocaleContext(HttpServletRequest request) {

        Locale locale = request.getLocale();

        String timezoneHeader = request.getHeader("TimeZone");
        TimeZone timeZone = getTimezone(timezoneHeader);

        return new CenteroLocaleContext(locale, timeZone);
    }


    private TimeZone getDefaultTimezone()
    {
        return TimeZone.getTimeZone("Asia/Seoul");
    }

    /**
     * timeZoneId에 해당하는 타임존 반환  유효하지 않을 경우 기본 Timezone반환
     * @param timeZoneId
     * @return
     */
    private TimeZone getTimezone(String timeZoneId)
    {
        try{
            return TimeZone.getTimeZone(timeZoneId);
        }catch (Exception e){
            return getDefaultTimezone();
        }
    }

}
