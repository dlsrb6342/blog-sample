package com.example.flux.fclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RoutingConfiguration {

    @Bean
    public RouterFunction<ServerResponse> route(FclientHandler fclientHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/fclient"), fclientHandler::client);
    }
}
