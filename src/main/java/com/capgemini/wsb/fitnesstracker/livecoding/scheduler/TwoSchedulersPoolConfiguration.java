package com.capgemini.wsb.fitnesstracker.livecoding.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.TaskScheduler;

@Configuration
public class TwoSchedulersPoolConfiguration {

    @Bean(name = "mainSchedulerPool")
    @Primary
    public TaskScheduler mainScheduler() {
        return createScheduler("Main-Scheduler", 10);
    }

    @Bean(name = "additionalSchedulerPool")
    public TaskScheduler additionalScheduler() {
        return createScheduler("Secondary-Scheduler", 10);
    }

    private TaskScheduler createScheduler(String threadNamePrefix, int poolSize) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(poolSize);
        scheduler.setThreadNamePrefix(threadNamePrefix);
        scheduler.initialize();
        return scheduler;
    }
}
