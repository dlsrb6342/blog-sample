package io.github.dlsrb6342;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
public class ReactorToolsDemoApplication {

    public static void main(String[] args) {
        ReactorDebugAgent.init();
        SpringApplication.run(ReactorToolsDemoApplication.class, args);
    }

}
