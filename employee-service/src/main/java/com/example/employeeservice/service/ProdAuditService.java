package com.example.employeeservice.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdAuditService implements IAuditService {
    
    @Override
    public String Audit() {
        return "Audit in Production Environment";
    }
    
}
