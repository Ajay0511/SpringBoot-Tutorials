package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.service.RabbitPublisher;

@RestController
@RequestMapping("/employee/rabbit")
public class RabbitController {
    private final RabbitPublisher rabbitPublisher;

    public RabbitController(RabbitPublisher rabbitPublisher) {
        this.rabbitPublisher = rabbitPublisher;
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestParam String message) {
        rabbitPublisher.publish("employee.exchange", "employee.routingkey", message);
        return "Message published to RabbitMQ: " + message;
    }
    
}
