package com.sleuth.example.service2;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service2Controller {

    @GetMapping("/toService2")
    public Map<String, Object> toService2() {
        return Map.of("service2", "success");
    }
}
