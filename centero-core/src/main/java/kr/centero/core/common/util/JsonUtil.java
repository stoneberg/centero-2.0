package kr.centero.core.common.util;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * JsonUtil:
 * Convert object to json string.
 */
public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final int LIMIT_LENGTH = 1500;
    private static ObjectMapper customObjectMapper;

    private JsonUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static ObjectMapper getCustomObjectMapper() {
        if (customObjectMapper == null) {
            customObjectMapper = new Jackson2ObjectMapperBuilder()
                    .failOnUnknownProperties(false) // SpringBoot default
                    .featuresToDisable(MapperFeature.DEFAULT_VIEW_INCLUSION) // SpringBoot default
                    .featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // SpringBoot default
                    .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_DATE))
                    .build();
        }
        return customObjectMapper;
    }

    public static String toJsonStr(Object object) {
        try {
            String jsonStr = getCustomObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
            return StringUtils.truncate(jsonStr, LIMIT_LENGTH).concat("... (truncated)");
        } catch (Exception ex) {
            log.error("Exception : {} \n", ExceptionUtils.getMessage(ex));
            return "";
        }
    }

    public static void prettyPrintRequestBody(Object object) {
        try {
            String jsonStr = getCustomObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
            if (log.isInfoEnabled() && jsonStr != null) {
                log.info("\n ▶️ ResponseBody = {} \n", jsonStr);
            }
        } catch (Exception ex) {
            log.error("Exception : {} \n", ExceptionUtils.getMessage(ex));
        }
    }

    public static void prettyPrintResponseBody(Object object) {
        try {
            String jsonStr = getCustomObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
            if (log.isInfoEnabled() && jsonStr != null) {
                if (jsonStr.length() <= LIMIT_LENGTH) {
                    log.info("\n ◀️️️ ResponseBody = {} \n", jsonStr);
                } else {
                    log.info("\n ◀️️️ ResponseBody = {} \n", StringUtils.truncate(jsonStr, LIMIT_LENGTH).concat("... (truncated)"));
                }
            }
        } catch (Exception ex) {
            log.error("Exception : {} \n", ExceptionUtils.getMessage(ex));
        }
    }

}
