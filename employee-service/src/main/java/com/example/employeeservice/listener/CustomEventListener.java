package com.example.employeeservice.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.employeeservice.events.MessageBusEvent;

@Component
public class CustomEventListener {
    
    @EventListener
    public void onMessageEvent(MessageBusEvent event) {

        System.out.println("🔥 Received Bus Event");
        System.out.println("From: " + event.getOriginService());
        System.out.println("Message: " + event.getMessage());
    }
}
