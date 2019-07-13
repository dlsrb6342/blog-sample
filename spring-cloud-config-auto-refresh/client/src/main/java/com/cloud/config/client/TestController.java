package com.cloud.config.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final TestProperties properties;

    public TestController(TestProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/foo1")
    public String getFoo1() {
        return properties.getFoo1();
    }

    @GetMapping("/foo2")
    public String getFoo2() {
        return properties.getFoo2();
    }

}
