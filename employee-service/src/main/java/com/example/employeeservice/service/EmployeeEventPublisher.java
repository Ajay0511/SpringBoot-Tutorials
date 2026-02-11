package com.example.employeeservice.service;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;


@Service
public class EmployeeEventPublisher {
    private final StreamBridge streamBridge;

    public EmployeeEventPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void publishEmployeeEvent(String message) {
        streamBridge.send("employeeEvent-out-0", message);
        System.out.println("Message sent to Kafka via StreamBridge: " + message);
    }
}
