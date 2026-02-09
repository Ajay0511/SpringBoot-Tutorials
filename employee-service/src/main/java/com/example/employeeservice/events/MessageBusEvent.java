package com.example.employeeservice.events;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class MessageBusEvent  extends RemoteApplicationEvent{
    private String message;

    public MessageBusEvent() {
        // Default constructor for deserialization
    }

    public MessageBusEvent(Object source, String originService, String message) {
        super(source, originService); // destination is null for broadcast
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
