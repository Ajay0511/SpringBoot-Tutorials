package com.example.employeeservice.health;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceHealthindicator implements HealthIndicator {
    
    @Override
    public Health health() {
        boolean employeeServiceUp = checkEmployeeServiceHealth();
        if(employeeServiceUp) {
            return Health.up().withDetail("EmployeeService", "Available").build();
        } else{
            return Health.down().withDetail("EmployeeService", "Not Available").build();
        }
    }

    private boolean checkEmployeeServiceHealth() {
        // Implement actual health check logic here
        return true; // Assuming service is healthy for this example
    }
}
