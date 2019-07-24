package com.example.mvc.mcaller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class McallerController {

    private final RestTemplate restTemplate;

    @GetMapping("/mcaller")
    public Map<String, String> call() {
        String s = restTemplate.getForObject("http://localhost:8083/mclient", String.class);

        return Map.of("mcaller", "caller", "mclient", s);
    }
}
