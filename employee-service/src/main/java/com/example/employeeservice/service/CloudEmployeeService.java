package com.example.employeeservice.service;

import org.springframework.stereotype.Service;

@Service
public class CloudEmployeeService {
    private final DepartmentClient departmentClient;

    public CloudEmployeeService(DepartmentClient departmentClient) {
        this.departmentClient = departmentClient;
    }

    public String fetchDepartmentName(Long empId) {
        return departmentClient.getDepartmentName(empId);
    }
}
