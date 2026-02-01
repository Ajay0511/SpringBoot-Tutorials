package com.example.employeeservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-client", url = "http://localhost:8081")
public interface DepartmentFeignClient {
    
    @GetMapping("/api/v1/department/new/{empId}")
    String getDepartment(@PathVariable int empId);
}
