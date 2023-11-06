package kr.centero.core.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * this annotation is used to convert UTC to user local time
 * put this annotation on the field(LocalDateTime) that you want to convert
 * ex) @UserLocalDateTime
 *     private LocalDateTime createdAt;
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLocalDateTime {
}
