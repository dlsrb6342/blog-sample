package com.sleuth.example.service2;

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
    public WebClient webClient(@Value("${service3.url}") String service3Url) {
        return WebClient.create(service3Url);
    }

    @Bean
    public RouterFunction<ServerResponse> route(Service2Handler service2Handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/toService2"), service2Handler::fromService1)
                .andRoute(RequestPredicates.GET("/all"), service2Handler::all);
    }
}
