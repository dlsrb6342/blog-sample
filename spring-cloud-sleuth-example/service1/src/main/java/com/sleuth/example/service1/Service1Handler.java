package com.sleuth.example.service1;

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
public class Service1Handler {

    private final WebClient webClient;

    public Mono<ServerResponse> toService2(ServerRequest request) {

        return webClient.get().uri("/fromService1")
                 .retrieve()
                 .bodyToMono(Map.class)
                 .flatMap(map -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                                           .body(BodyInserters.fromObject(map)));
    }

    public Mono<ServerResponse> all(ServerRequest request) {

        return webClient.get().uri("/all")
                        .retrieve()
                        .bodyToMono(Map.class)
                        .flatMap(map -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                                                      .body(BodyInserters.fromObject(map)));
    }

}
