package com.sleuth.example.service2;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class Service2Handler {

    private final WebClient webClient;

    public Mono<ServerResponse> fromService1(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(Map.of("message", "service1 -> service2")));
    }

    public Mono<ServerResponse> all(ServerRequest request) {

        return webClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/all")
                                                     .queryParam("service2", "passed")
                                                     .build())
                        .retrieve()
                        .bodyToMono(Map.class)
                        .flatMap(map -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                                                  .body(BodyInserters.fromObject(map)));
    }
}
