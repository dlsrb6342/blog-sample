package com.cloud.gateway.filter;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderedCustomGatewayFilterFactory
        extends AbstractGatewayFilterFactory<OrderedCustomGatewayFilterFactory.Config> {

    public static final String ONE_KEY = "one";
    public static final String TWO_KEY = "two";

    public OrderedCustomGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of(ONE_KEY, TWO_KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter((exchange, chain) -> {
            log.debug("ORDERED one : {} / two : {}", config.getOne(), config.getTwo());
            return chain.filter(exchange);
        }, Ordered.HIGHEST_PRECEDENCE);
    }

    @Getter
    @Setter
    public static class Config {
        private String one;
        private String two;
    }
}
