package com.example.employeeservice.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevAuditService implements IAuditService {
    
    @Override
    public String Audit() {
        return "Audit in Development Environment";
    }
}
