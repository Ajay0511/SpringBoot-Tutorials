package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee/tracing")
public class EmpoyeeTracingController {
    @GetMapping("/test")
    public String testTracing() {
        return "Tracing is working!";
    }
}
