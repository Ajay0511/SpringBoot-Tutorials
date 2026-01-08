package com.example.employeeservice.service;

import org.springframework.stereotype.Service;

import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.dto.EmployeeDto;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    /*
    In real applications, repositories should only deal with entities and persistence logic. 
    DTOs belong to the service and controller layers to decouple API contracts from database models.
     In this example, DTOs are stored in the service layer only to simulate data storage before introducing JPA.
    */
    private final Map<Long, EmployeeDto> employeeDataStore = new HashMap<>();

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeDto create(EmployeeDto employeeDto) {
        EmployeeDto employee = new EmployeeDto(
            (long) employeeDataStore.size() + 1,
            employeeDto.getName(),
            employeeDto.getDepartment()
        );
        employeeDataStore.put(employee.getId(), employee);
        return employee;
    }

    public List<EmployeeDto> getAll() {
        return new ArrayList<>(employeeDataStore.values());
    }

    public EmployeeDto getById(long id) {
        return employeeDataStore.get(id);
    }

    public String fetchEmployee() {
        return repository.getEmployee();
    }
}
