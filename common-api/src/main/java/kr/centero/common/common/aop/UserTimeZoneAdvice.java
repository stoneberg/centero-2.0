package kr.centero.common.common.aop;

import kr.centero.core.common.annotations.UserLocalDateTime;
import kr.centero.core.common.exception.ApiErrorCode;
import kr.centero.core.common.exception.ApiException;
import kr.centero.core.common.interceptor.timezone.TimeZoneContextHolder;
import kr.centero.core.common.payload.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

/**
 * This advice is used to convert the timezone of the response data to the user's timezone.
 */
@Slf4j
@Aspect
@Component
public class UserTimeZoneAdvice {
    @AfterReturning(
            pointcut = "execution(* kr.centero.common.*.*.web.*.*(..)))",
            returning = "responseEntity"
    )
    public void afterReturning(JoinPoint joinPoint, ResponseEntity<?> responseEntity) {
        if (responseEntity.getBody() instanceof ApiResponse<?> apiResponse) {
            Object data = apiResponse.getData();

            // Check if the data requires timezone conversion
            if (data != null) {
                convertTimeZoneIfNecessary(data);
            }
        }
    }

    private void convertTimeZoneIfNecessary(Object data) {
        ZoneId zoneId = TimeZoneContextHolder.getZoneId();
        if (zoneId == null) {
            return; // or throw an exception if the zone ID must not be null
        }

        // Check if the data is a collection and handle accordingly
        if (data instanceof Collection<?>) {
            ((Collection<?>) data).forEach(this::convertTimeZoneOfObjectIfNecessary);
        } else {
            // Handle a single object
            convertTimeZoneOfObjectIfNecessary(data);
        }
    }

    private void convertTimeZoneOfObjectIfNecessary(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            UserLocalDateTime annotation = field.getAnnotation(UserLocalDateTime.class);
            if (annotation != null && field.getType().equals(LocalDateTime.class)) {
                boolean isAccessible = field.canAccess(object);
                if (!isAccessible) {
                    field.setAccessible(true);
                }

                try {
                    LocalDateTime originalDateTime = (LocalDateTime) field.get(object);
                    if (originalDateTime != null) {
                        LocalDateTime convertedDateTime = convertToLocalDateTimeInZone(originalDateTime, TimeZoneContextHolder.getZoneId());
                        // Instead of setting the field directly, use a setter method if available
                        // This is to comply with encapsulation principles
                        String setterMethodName = "set" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
                        Method setterMethod = object.getClass().getMethod(setterMethodName, LocalDateTime.class);
                        setterMethod.invoke(object, convertedDateTime);
                    }
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    throw new ApiException("Failed to convert timezone", e, ApiErrorCode.TIMEZONE_CONVERSION_FAILURE);
                }
            }
        }
    }

    private LocalDateTime convertToLocalDateTimeInZone(LocalDateTime dateTime, ZoneId zoneId) {
        return dateTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(zoneId).toLocalDateTime();
    }
}


