package com.example.employeeservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepartmentClient {
    private final RestTemplate restTemplate;

    public DepartmentClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDepartmentName(Long empId){
        //Service name instead of URL for load balancing
        return restTemplate.getForObject("http://localhost:8081/api/v1/department/" + empId, String.class);
    }
}
