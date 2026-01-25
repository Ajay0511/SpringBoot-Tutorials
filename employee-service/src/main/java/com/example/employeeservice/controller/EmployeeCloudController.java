package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.service.CloudEmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/cloudemployee")
public class EmployeeCloudController {

    @Autowired
    private CloudEmployeeService cloudEmployeeService;

    @GetMapping("/{empId}/departmentname")
    public String getDepartmentName(@PathVariable Long empId) {
        return cloudEmployeeService.fetchDepartmentName(empId);
    }
}
