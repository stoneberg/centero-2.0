package kr.centero.common.config;

import feign.Logger;
import feign.RequestInterceptor;
import kr.centero.core.common.interceptor.feign.FeignClientInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OpenFeignConfig {
    private final FeignClientInterceptor feignClientInterceptor;

    @Bean
    Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return feignClientInterceptor;
    }
}
