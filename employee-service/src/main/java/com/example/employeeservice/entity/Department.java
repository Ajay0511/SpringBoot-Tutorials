package com.example.employeeservice.entity;

public class Department {
    private int empId;
    private String name;
    private String location;

    public Department(int empId, String name, String location) {
        this.empId = empId;
        this.name = name;
        this.location = location;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}