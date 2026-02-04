package com.example.employeeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeservice.client.DepartmentFeignClient;
import com.example.employeeservice.entity.Department;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class DepartmentFeignService {

    @Autowired
    private DepartmentFeignClient departmentFeignClient;

    private static final String DEPARTMENT_SERVICE = "department-service";


    @CircuitBreaker(name =  DEPARTMENT_SERVICE, fallbackMethod = "departmentFallback")
    public String getDepartmentName(int empId) {
        return departmentFeignClient.getDepartment(empId);
    }

    public String departmentFallback(int empId, Throwable throwable) {
        return "Default Department";
    }
    
}
