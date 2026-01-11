package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.service.EmployeeService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("v1/hello")
    public String hello() {
        return "Employee Service is called";
    }

    // @GetMapping("v1/employee")
    // public String getEmployee() {
    //     return service.fetchEmployee(); 
    // }

    @PostMapping("v1/create")
    public ResponseEntity<EmployeeDto> createEmployeeDto(@Valid @RequestBody EmployeeDto employeeDto) {        
        EmployeeDto created = service.create(employeeDto);

        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @GetMapping("v1/all")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("v1/getById")
    public ResponseEntity<EmployeeDto> getById(long id) {
        return ResponseEntity.ok(service.getById(id)); 
    }

    @PostMapping("v2/create")
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        return service.create(employeeDto);
    }

    @GetMapping("/v2/getById/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/v2/update/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return service.update(id, employeeDto);
    }

    @DeleteMapping("/v2/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        service.delete(id);
    }
    
    

    
}
