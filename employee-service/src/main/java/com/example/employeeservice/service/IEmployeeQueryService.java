package com.example.employeeservice.service;

import java.util.List;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;

public interface IEmployeeQueryService {
    List<Employee> getAllEmployeesJPQL();
    List<Employee> getByDepartmentJPQL(String dept);
    List<EmployeeDto> gEmployeeDtos();

    List<Employee> getAllNative();
    List<Employee> getTopPaid();
    List<String> getNameDepartmentNative();

    
}
