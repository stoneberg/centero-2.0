package kr.centero.common.common.util;

import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


@Component
public class EnvUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        EnvUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static String getProperty(String key) {
        return applicationContext.getEnvironment().getProperty(StringUtils.trimToEmpty(key));
    }

    public static String getProperty(String key, String defaultValue) {
        return applicationContext.getEnvironment().getProperty(StringUtils.trimToEmpty(key), defaultValue);
    }

    public static Resource getResourceFile(String resourcePath) {
        return applicationContext.getResource(String.format(!resourcePath.startsWith("classpath:") ? "classpath:%s" : "%s", StringUtils.trimToEmpty(resourcePath)));
    }

    public static String getActiveProfile() {
        String activeProfile = StringUtils.isNotBlank("spring.profiles.active") ? getProperty("spring.profiles.active") : "";
        if (StringUtils.trimToEmpty(activeProfile).equalsIgnoreCase("prod")) {
            return "PROD";
        } else if (StringUtils.trimToEmpty(activeProfile).equalsIgnoreCase("dev")) {
            return "DEV";
        } else if (StringUtils.trimToEmpty(activeProfile).equalsIgnoreCase("local")) {
            return "LOCAL";
        } else {
            return "DEFAULT";
        }
    }

    public static String getApplicationName() {
        return applicationContext.getEnvironment().getProperty("spring.application.name");
    }

    public static String getServerPort() {
        return applicationContext.getEnvironment().getProperty("server.port");
    }

}
