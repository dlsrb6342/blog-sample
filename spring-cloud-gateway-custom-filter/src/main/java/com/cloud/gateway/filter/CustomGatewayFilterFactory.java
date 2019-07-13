package com.cloud.gateway.filter;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomGatewayFilterFactory
        extends AbstractGatewayFilterFactory<CustomGatewayFilterFactory.Config> {

    public static final String FOO_KEY = "foo";
    public static final String BAR_KEY = "bar";

    public CustomGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of(FOO_KEY, BAR_KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.debug("NOT ORDERED Foo : {} / Bar: {}", config.getFoo(), config.getBar());
            return chain.filter(exchange);
        };
    }

    @Getter
    @Setter
    public static class Config {
        private String foo;
        private String bar;
    }
}
