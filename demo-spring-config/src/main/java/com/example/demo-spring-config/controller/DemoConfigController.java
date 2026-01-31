package com.example.demo_spring_config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/demo/config")
public class DemoConfigController {
    
    @Value("${demo.message:Default Demo Message}")
    private String message;

    @GetMapping("/message")
    public String getMessage() {
        return message;
    }
}
