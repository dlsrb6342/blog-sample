package com.sleuth.example.service3;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class Service3Handler {

    public Mono<ServerResponse> all(ServerRequest request) {
        Map<String, String> resBody = new HashMap<>();
        resBody.put("service3", "passed");
        request.queryParam("service2").ifPresent(s -> resBody.put("service2", s));

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(resBody));
    }
}
