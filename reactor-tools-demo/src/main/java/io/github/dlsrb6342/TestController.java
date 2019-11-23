package io.github.dlsrb6342;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class TestController {

    @GetMapping("/test")
    public Mono<String> test(@RequestParam("error") boolean error) {
        return Mono.just("string")
                   .flatMap(str -> {
                       if (error) {
                           return Mono.error(new RuntimeException("Exception Occurs"));
                       } else {
                           return Mono.just(str);
                       }
                   })
                   .map(String::toUpperCase)
                   .map(str -> str + " more");
    }
}
