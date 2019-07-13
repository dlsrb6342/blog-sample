package com.content.caching.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
public class TestController {

    @PostMapping("/test")
    public ResBody test(@RequestBody ReqBody reqBody) {
        return new ResBody("test", 100);
    }

    @Data
    private static class ReqBody {
        private String foo;
        private String bar;
    }

    @AllArgsConstructor
    @Data
    private static class ResBody {
        private String result;
        private int code;
    }
}
