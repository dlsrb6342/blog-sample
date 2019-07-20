package com.sleuth.example.service3;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SchedulingComponent {

    private final WebClient webClient = WebClient.create();

    @Scheduled(
            initialDelayString = "10000",
            fixedDelayString = "500"
    )
    public void scheduledJob() {
        webClient.get().uri("https://httpbin.org/anything").retrieve();
    }
}
