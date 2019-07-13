package com.cloud.config.client;

import static org.springframework.util.StringUtils.hasText;

import java.io.Closeable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.PostConstruct;

import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ConfigGitClientWatch implements Closeable, EnvironmentAware {

    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicReference<String> version = new AtomicReference<>();
    private final ContextRefresher refresher;
    private final ConfigServicePropertySourceLocator locator;

    private Environment environment;

    public ConfigGitClientWatch(
            ContextRefresher refresher, ConfigServicePropertySourceLocator locator) {
        this.refresher = refresher;
        this.locator = locator;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void start() {
        running.compareAndSet(false, true);
    }

    @Scheduled(
            initialDelayString = "${spring.cloud.config.watch.git.initialDelay:10000}",
            fixedDelayString = "${spring.cloud.config.watch.git.delay:1000}"
    )
    public void watchConfigServer() {
        if (running.get()) {
            String newVersion = fetchNewVersion();
            String oldVersion = version.get();

            if (versionChanged(oldVersion, newVersion)) {
                version.set(newVersion);
                refresher.refresh();
            }
        }
    }

    private String fetchNewVersion() {
        CompositePropertySource propertySource = (CompositePropertySource) locator.locate(environment);
        return (String) propertySource.getProperty("config.client.version");
    }

    private static boolean versionChanged(String oldVersion, String newVersion) {
        return !hasText(oldVersion) && hasText(newVersion)
               || hasText(oldVersion) && !oldVersion.equals(newVersion);
    }

    @Override
    public void close() {
        running.compareAndSet(true, false);
    }
}