package com.sleuth.example.service1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class Service1Handler {

    private final WebClient webClient;

    public Mono<ServerResponse> toService2(ServerRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("service1", "success");

        return webClient.get().uri("/toService2")
                        .retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                        .flatMap(res ->{
                            result.putAll(res);
                            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                                                 .body(BodyInserters.fromObject(result));
                        });
    }

}
