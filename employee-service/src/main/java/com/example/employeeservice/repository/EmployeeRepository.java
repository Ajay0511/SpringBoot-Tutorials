package com.example.employeeservice.repository;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {
    public String getEmployee(){
        return "Employee from Employee Repository";
    }
}
