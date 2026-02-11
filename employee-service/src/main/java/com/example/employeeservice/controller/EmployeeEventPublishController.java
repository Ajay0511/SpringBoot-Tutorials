package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.service.EmployeeEventPublisher;

@RestController
@RequestMapping("/employee/events")
public class EmployeeEventPublishController {
    
    private final EmployeeEventPublisher eventPublisher; 

    public EmployeeEventPublishController(EmployeeEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/publish")
    public String publishEmployeeEvent(@RequestParam String employeeId, @RequestParam String employeeName) {
        String message = "New employee created with ID: " + employeeId + " and Name: " + employeeName;
        eventPublisher.publishEmployeeEvent(message);
        return "Employee event published successfully!";
    }
}
