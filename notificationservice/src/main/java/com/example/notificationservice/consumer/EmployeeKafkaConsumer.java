package com.example.notificationservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeKafkaConsumer {
    @KafkaListener(topics = "employee-topic", groupId = "employee-group")
    public void consume(String message) {
        System.out.println("✅ Message received in Notification Service using kafka: " + message);
    }
}

