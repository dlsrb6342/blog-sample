package com.example.mvc.mclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MclientController {

    @GetMapping("/mclient")
    public String client() {
        return "client";
    }
}
