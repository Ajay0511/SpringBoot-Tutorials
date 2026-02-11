package com.example.notificationservice.event;

public class NewEmployeeEvent {
    private String employeeId;
    private String name;
    private String action;

    public NewEmployeeEvent() {
    }

    public NewEmployeeEvent(String employeeId, String name, String action) {
        this.employeeId = employeeId;
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

}
