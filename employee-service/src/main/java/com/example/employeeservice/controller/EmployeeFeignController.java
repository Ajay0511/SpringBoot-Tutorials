package com.example.employeeservice.controller;
import com.example.employeeservice.service.DepartmentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.client.DepartmentFeignClient;
import com.example.employeeservice.entity.Department;

@RestController
@RequestMapping("/employees")
public class EmployeeFeignController {

    @Autowired
    private DepartmentFeignService departmentFeignService;

    @Autowired
    private DepartmentFeignClient departmentFeignClient;

    EmployeeFeignController(DepartmentFeignService departmentFeignService) {
        this.departmentFeignService = departmentFeignService;
    }

    @GetMapping("/feign/{empId}")
    public String getDepartmentName(@PathVariable int empId) {
        System.out.println("Inside EmployeeFeignController.getDepartmentName with empId: " + empId);
        return departmentFeignClient.getDepartment(empId);
    }

    @GetMapping("/resillence/{empId}")
    public String getDepartmentWithResilience(@PathVariable int empId) {
        return departmentFeignService.getDepartmentName(empId);
    }
}
