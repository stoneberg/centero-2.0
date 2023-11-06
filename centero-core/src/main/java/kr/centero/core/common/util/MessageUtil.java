package kr.centero.core.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Accept-Language: ko-KR, en-US, zh-CN
 */
@Component
@RequiredArgsConstructor
public class MessageUtil {
    private final MessageSource messageSource;

    /**
     * Get message from message source.
     *
     * @param code message code
     * @return message
     */
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, getCurrentLocale());
    }


    /**
     * Get message from message source.
     *
     * @param code   message code
     * @param locale locale
     * @return message
     */
    public String getMessage(String code, Locale locale) {
        return messageSource.getMessage(code, null, locale);
    }

    /**
     * Get message from message source.
     *
     * @param code         message code
     * @param placeHolders place holders
     * @return message
     */
    public String getMessage(String code, Object[] placeHolders) {
        return messageSource.getMessage(code, placeHolders, getCurrentLocale());
    }

    /**
     * Get message from message source.
     *
     * @param code         message code
     * @param placeHolders place holders
     * @param locale       locale
     * @return message
     */
    public String getMessage(String code, Object[] placeHolders, Locale locale) {
        return messageSource.getMessage(code, placeHolders, locale);
    }

    /**
     * Get current locale.
     *
     * @return current locale
     */
    public Locale getCurrentLocale() {
        return LocaleContextHolder.getLocale();
    }

}
