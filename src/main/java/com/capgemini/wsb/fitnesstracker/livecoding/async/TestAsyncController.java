package com.capgemini.wsb.fitnesstracker.livecoding.async;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/v1/async")
@AllArgsConstructor
public class TestAsyncController {

    private final ServiceImpl service;
    private static final Logger log = LoggerFactory.getLogger(TestAsyncController.class);

    @GetMapping
    public String dispatchTask() {
        log.info("Task is going to be dispatched");
        service.doSomething();
        return "Task dispatched";
    }
}
