package kr.centero.ghg.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import kr.centero.core.common.interceptor.logging.LoggingInterceptor;
import kr.centero.core.common.interceptor.mdc.MdcInterceptor;
import kr.centero.core.common.interceptor.timezone.LocaleTimeZoneInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class GhgAppConfig implements WebMvcConfigurer {
    private final MdcInterceptor mdcInterceptor;
    private final LoggingInterceptor loggingInterceptor;
    private final LocaleTimeZoneInterceptor localeTimeZoneInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
        registry.addInterceptor(mdcInterceptor);
        // Locale 및 TimeZone 을 LocaleTimeZoneContextHolder에 담는 처리 필터
        registry.addInterceptor(localeTimeZoneInterceptor);
    }

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .failOnUnknownProperties(false) // SpringBoot default
                .featuresToDisable(MapperFeature.DEFAULT_VIEW_INCLUSION) // SpringBoot default
                .featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // SpringBoot default
                .serializerByType(LocalDateTime.class,
                        new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_DATE));
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public MessageSource validationMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages/validation");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * DTO validation message source
     * 
     * @return load validation message from messages/validation.properties depend on
     *         locale in request header
     */
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(validationMessageSource());
        return bean;
    }

    // avoid circular reference in Spring Security Config
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 로케일 리절버
     * @return
     */
    @Bean
    LocaleResolver localeResolver()
    {
        AcceptHeaderLocaleResolver centeroLocalResolver = new AcceptHeaderLocaleResolver();
        // 한국어 기본
        centeroLocalResolver.setDefaultLocale(Locale.KOREA);
        // 지원하는 Locale : 한국, 미국
        centeroLocalResolver.setSupportedLocales(Arrays.asList(Locale.KOREA, Locale.US));
        return centeroLocalResolver;
    }
}
