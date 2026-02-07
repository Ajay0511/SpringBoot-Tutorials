package com.example.employeeservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalCallService {
    
    private final RestTemplate restTemplate = new RestTemplate();

    public String call() {
        return restTemplate.getForObject("http://localhost:8080/employee/tracing/test", String.class);
    }
}
