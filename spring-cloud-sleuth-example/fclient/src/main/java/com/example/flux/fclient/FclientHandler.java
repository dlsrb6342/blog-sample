package com.example.flux.fclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class FclientHandler {

    public Mono<ServerResponse> client(ServerRequest request) {

        return ServerResponse.ok().body(BodyInserters.fromObject("client"));
    }
}
