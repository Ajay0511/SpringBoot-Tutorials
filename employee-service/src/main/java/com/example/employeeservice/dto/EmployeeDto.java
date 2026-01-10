package com.example.employeeservice.dto;

import jakarta.validation.constraints.NotBlank;

public class EmployeeDto {
    private  Long id;
    private String name;
    private String department;

    public EmployeeDto() {
        //required for Jackson deserialization
    }

    public EmployeeDto(Long id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    //Bean Validation to avoid garbage data stored in DB
    @NotBlank(message = "Name cannot be blank")
    public String getName() {
        return name;
    }

    @NotBlank(message = "Department cannot be blank")
    public String getDepartment() {
        return department;
    }


}
