package com.capgemini.wsb.fitnesstracker.livecoding.scheduler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

@Component
public class MySpringScheduler {

    private static final Logger log = LoggerFactory.getLogger(MySpringScheduler.class);

    private final TaskScheduler mainScheduler;
    private final TaskScheduler additionalScheduler;

    public MySpringScheduler(
            @Qualifier("mainSchedulerPool") TaskScheduler mainScheduler,
            @Qualifier("additionalSchedulerPool") TaskScheduler additionalScheduler) {
        this.mainScheduler = mainScheduler;
        this.additionalScheduler = additionalScheduler;
    }

    @PostConstruct
    public void startScheduling() {
        mainScheduler.schedule(this::scheduleMonthlyTask, new CronTrigger("0 0 0 1 * ?")); //miesiecznie
        mainScheduler.schedule(this::scheduleMinuteTask, new CronTrigger("0 * * * * ?")); //minutowo

//        mainScheduler.scheduleAtFixedRate(this::scheduleTask1, 5000);
//        additionalScheduler.scheduleAtFixedRate(this::scheduleTask2, 3000);
//        additionalScheduler.scheduleWithFixedDelay(this::scheduleTask3, 5000);
    }
    public void scheduleMinuteTask() {
        log.info("Scheduled minute task executed");
    }
    public void scheduleMonthlyTask() {
        log.info("Scheduled monthly task executed");
    }
    public void scheduleTask1() {
        log.info("Scheduled task 1");
    }

    public void scheduleTask2() {
        log.info("Scheduled task 2");
    }

    public void scheduleTask3() {
        log.info("Scheduled task 3");
    }
}
