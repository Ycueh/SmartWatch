package net.lab1024.sa.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Configuration for asynchronous task execution threads.
 */
@Slf4j
@Configuration
public class AsyncConfig {

    /**
     * Bean name for the thread pool configuration.
     */
    public static final String ASYNC_EXECUTOR_THREAD_NAME = "smart-admin-async-executor";

    /**
     * Configure the thread pool.
     *
     * @return AsyncTaskExecutor
     */
    @Bean(name = ASYNC_EXECUTOR_THREAD_NAME)
    public AsyncTaskExecutor executor() {
        int processors = Runtime.getRuntime().availableProcessors();
        int threadCount = Math.max(1, processors - 1);
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // Number of core threads
        taskExecutor.setCorePoolSize(threadCount);
        // Maximum number of threads
        taskExecutor.setMaxPoolSize(threadCount);
        taskExecutor.setThreadNamePrefix(ASYNC_EXECUTOR_THREAD_NAME);
        taskExecutor.initialize();
        return taskExecutor;
    }

    /**
     * Configuration for exceptions in Spring's asynchronous tasks.
     */
    @Configuration
    public static class AsyncExceptionConfig implements AsyncConfigurer {
        @Override
        public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
            return new AsyncExceptionHandler();
        }
    }

    /**
     * Custom exception handling.
     */
    public static class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            log.error("Asynchronous task encountered an exception: {}, Parameters: {}, ", method.getDeclaringClass().getSimpleName() + "." + method.getName(), Arrays.toString(objects), throwable);
        }
    }
}
