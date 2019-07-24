package com.example.flux.fcaller;

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
public class FcallerHandler {

    private final WebClient webClient;

    public Mono<ServerResponse> call(ServerRequest request) {

        return webClient.get().uri("http://localhost:8081/fclient").retrieve()
                .bodyToMono(String.class)
                .flatMap(s -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromObject(Map.of("fserver", "server", "fclient", s))));
    }
}
