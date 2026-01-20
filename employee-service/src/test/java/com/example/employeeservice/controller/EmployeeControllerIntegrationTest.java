package com.example.employeeservice.controller;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.security.JwtUtil;
import com.example.employeeservice.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @MockitoBean
    private JwtUtil jwtUtil; 

    @Test
    void getEmployeesV1_shouldReturnList() throws Exception {
        List<EmployeeDto> list = List.of(
            new EmployeeDto(1L, "Ajay", "IT"),
            new EmployeeDto(2L, "Sameer", "HR")
        );

        when(employeeService.getAll()).thenReturn(list);

        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }
}
