package com.example.employeeservice.dto;

public class AuthRequest {
    private String username;
    private String password;

    // No-args constructor
    public AuthRequest() {}

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
