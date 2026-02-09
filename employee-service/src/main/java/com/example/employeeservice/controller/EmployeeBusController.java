package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.events.MessageBusEvent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationEventPublisher;

@RestController
@RefreshScope
@RequestMapping("/employee/bus")
public class EmployeeBusController {
    @Value("${employee.message}")
    private String message;

    private ApplicationEventPublisher eventPublisher;
    private BusProperties busProperties;

    public EmployeeBusController(ApplicationEventPublisher eventPublisher, BusProperties busProperties) {
        this.eventPublisher = eventPublisher;
        this.busProperties = busProperties;
    }

    @GetMapping("/msg")
    public String busMsg() {
        return message;
    }

    @PostMapping("/publish")
    public String publishEvent(@RequestParam String msg) {
        MessageBusEvent event = new MessageBusEvent(this, busProperties.getId(), msg);
        eventPublisher.publishEvent(event);
        return "Event Published: " + msg;
    }   


}
