package com.example.employeeservice.dto;

public class EmployeeEvent {
    
    private String eventType;
    private String message;

    public EmployeeEvent() {
    }

    public EmployeeEvent(String eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
