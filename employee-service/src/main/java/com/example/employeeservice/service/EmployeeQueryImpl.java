package com.example.employeeservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepository;

@Service
public class EmployeeQueryImpl implements IEmployeeQueryService{
    private final EmployeeRepository employeeRepository;

    public EmployeeQueryImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployeesJPQL(){
        return this.employeeRepository.findAllEmployeesJPQL();
    }

    public List<Employee> getByDepartmentJPQL(String dept){
        return this.employeeRepository.findByDepartmentJPQL(dept);
    }

    public List<EmployeeDto> gEmployeeDtos(){
        return this.employeeRepository.getEmployeeDtos();
    }

    public List<Employee> getAllNative(){
        return this.employeeRepository.getAllEmployeesNative();
    }
    
    public List<Employee> getTopPaid(){
        return this.employeeRepository.findTop5HighestPaid();
    }
    
   public List<String> getNameDepartmentNative() {
        List<Object[]> rows = this.employeeRepository.fetchNameAndDepartmentNative();
        List<String> result = new ArrayList<>();

        for (Object[] row : rows) {
            result.add(row[0] + " - " + row[1]);
        }
        return result;
    }

}



