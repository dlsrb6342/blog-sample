package com.sleuth.example.service1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RoutingConfiguration {

    @Bean
    public WebClient webClient(@Value("${service2.url}") String service2Url) {
        return WebClient.create(service2Url);
    }

    @Bean
    public RouterFunction<ServerResponse> route(Service1Handler service1Handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/toService2"), service1Handler::toService2);
    }

}
