package kr.centero.common.api.common.interceptor.mdc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Mapped Diagnostic Context(MDC)를 비동기로 사용하기 위한 설정
 */
@EnableAsync
@Configuration
public class MdcAsyncConfig {
    @Bean(name = "mdcThreadPoolTaskExecutor")
    public Executor asyncThreadTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(8);
        threadPoolTaskExecutor.setMaxPoolSize(8);
        threadPoolTaskExecutor.setTaskDecorator(new MdcAsyncTaskDecorator());
        threadPoolTaskExecutor.setThreadNamePrefix("MDC");
        return threadPoolTaskExecutor;
    }
}
