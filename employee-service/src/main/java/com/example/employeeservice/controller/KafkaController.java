package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.service.KafkaProducerService;

@RestController
@RequestMapping("/employee/kafka")
public class KafkaController {
    private final KafkaProducerService kafkaProducerService;

    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestParam String message) {
        kafkaProducerService.sendMessage(message);
        return "Message published to Kafka: " + message;
    }

}
