package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.service.IAuditService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/employees")
public class AuditController {
    private final IAuditService auditService;

    public AuditController(IAuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("/audit")
    public String getMethodName() {
        return auditService.Audit();
    }
    
}
