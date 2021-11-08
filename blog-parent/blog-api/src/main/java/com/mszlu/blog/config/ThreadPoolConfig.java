package com.mszlu.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync //開啟多線程
public class ThreadPoolConfig {

    @Bean("taskExecutor")
    public Executor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // set核心thread數
        executor.setCorePoolSize(5);
        // 最大thread數
        executor.setMaxPoolSize(20);
        //配置queue大小
        executor.setQueueCapacity(Integer.MAX_VALUE);
        // thread活躍時間（秒）
        executor.setKeepAliveSeconds(60);
        // thread預設名稱
        executor.setThreadNamePrefix("碼戲團blog");
        // 等待所有task结束后再關閉thread pool
        executor.setWaitForTasksToCompleteOnShutdown(true);

        executor.initialize();
        return executor;
    }
}
