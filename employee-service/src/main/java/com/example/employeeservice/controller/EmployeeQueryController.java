package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.service.IEmployeeQueryService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/query")
public class EmployeeQueryController {
    /*
    
    */
    private final IEmployeeQueryService service;

    public EmployeeQueryController(IEmployeeQueryService employeeQueryService){
        this.service = employeeQueryService;
    }

    //JPQL
    @GetMapping("/jpql")
    public List<Employee> getAllJpql(){
        return this.service.getAllEmployeesJPQL();
    }

    @GetMapping("/jpql/{dept}")
    public List<Employee> getByDeptJpql(@PathVariable String dept) {
        return this.service.getByDepartmentJPQL(dept);
    }

    // ---------- NATIVE ----------

    @GetMapping("/native")
    public List<Employee> getAllNative() {
        return service.getAllNative();
    }

    @GetMapping("/native/top")
    public List<Employee> getTopPaid() {
        return service.getTopPaid();
    }

    @GetMapping("/native/simple")
    public List<String> getNameDepartment() {
        return service.getNameDepartmentNative();
    }
    
    

}
