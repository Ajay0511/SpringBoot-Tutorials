package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.service.EmployeeService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Employee Service is called";
    }

    @GetMapping("/employee")
    public String getEmployee() {
        return service.fetchEmployee(); 
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployeeDto(@RequestBody EmployeeDto employeeDto) {        
        EmployeeDto created = service.create(employeeDto);

        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    
    @GetMapping("/getById")
    public ResponseEntity<EmployeeDto> getById(long id) {
        return ResponseEntity.ok(service.getById(id)); 
    }
    
}
