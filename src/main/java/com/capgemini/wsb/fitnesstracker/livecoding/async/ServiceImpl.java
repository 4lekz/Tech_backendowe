package com.capgemini.wsb.fitnesstracker.livecoding.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(ServiceImpl.class);

    @Async("async-pool")
    public void doSomething() {
        try {
            Thread.sleep(10000); // Simulate long-running task
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("doSomething() completed");
    }
}
