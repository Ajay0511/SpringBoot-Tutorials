package com.example.department_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    @GetMapping("/{id}")
    public String getDepartment(@PathVariable Long id) {
        // Just returning mock data for now
        return "IT";
    }
}

