package com.example.employeeservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeService;

/**
 * EmployeeServiceTest demonstrates unit testing for EmployeeService.
 * Uses Mockito to mock the repository and test service logic without hitting the DB.
 */
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    // Mocking the repository so that we don't hit the database
    @Mock
    EmployeeRepository repository;

    // Injecting mocks into the service so that the service uses the mocked repository
    @InjectMocks
    EmployeeService service;

    // ===================== DTO LAYER TESTS =====================
    // These tests work with the in-memory DTO store inside EmployeeService
    // No real database calls happen here

    @Test
    void createDto_shouldStoreSuccessfully() {
        // Simulate the database saving an entity (entity returned with ID)
        Employee savedEntity = new Employee(1L, "Ajay", "IT");

        // Mockito: when save() is called with any Employee object, return the savedEntity
        when(repository.save(any(Employee.class)))
                .thenReturn(savedEntity);

        // Prepare DTO input to create() method
        EmployeeDto dto = new EmployeeDto(1L, "Ajay", "IT");

        // Call service create() which maps DTO -> Entity -> stores in memory
        EmployeeDto result = service.create(dto);

        // Assertions to ensure DTO has been stored and ID is assigned
        assertNotNull(result.getId(), "Created DTO should have an ID assigned");
        assertEquals("Ajay", result.getName(), "Name should match input DTO");
    }

    @Test
    void getAllDto_shouldReturnAll() {
        // Populate the in-memory DTO store using service.update()
        service.update(1L, new EmployeeDto(1L, "A", "IT"));
        service.update(2L, new EmployeeDto(2L, "B", "HR"));

        // Fetch all DTOs
        List<EmployeeDto> result = service.getAll();

        // Check if all DTOs were returned
        assertEquals(2, result.size(), "There should be exactly 2 DTOs in the store");
    }

    @Test
    void deleteDto_shouldRemoveFromStore() {
        // Mock repository save call so create() works
        when(repository.save(any(Employee.class)))
                .thenReturn(new Employee(11L, "Ajay", "IT"));

        // Create a DTO in the in-memory store
        EmployeeDto saved = service.create(
                new EmployeeDto(11L, "Ajay", "IT")
        );

        // Delete the DTO using the service
        service.delete(saved.getId());

        // Verify it was actually removed from in-memory store
        assertNull(service.getById(saved.getId()), "DTO should be null after deletion");
    }

    // ===================== ENTITY LAYER TESTS =====================
    // These tests interact with the repository and verify calls using Mockito

    @Test
    void createEntity_shouldCallRepository() {
        // Prepare entity without ID (typical for JPA create)
        Employee emp = new Employee(null, "Ajay", "IT");
        // Simulate repository returning entity with ID assigned by DB
        Employee saved = new Employee(1L, "Ajay", "IT");

        // Mock repository save() behavior
        when(repository.save(emp)).thenReturn(saved);

        // Call service method that saves entity
        Employee result = service.createEmployee(emp);

        // Assertions
        assertEquals(1L, result.getId(), "Saved entity should have ID assigned by repository");

        // Verify repository.save was called exactly once with emp
        verify(repository).save(emp);
    }

    @Test
    void deleteEntity_shouldCallRepository() {
        // Mockito: doNothing() because deleteById() returns void
        doNothing().when(repository).deleteById(1L);

        // Call service delete
        service.deleteEmployee(1L);

        // Verify repository deleteById() was invoked
        verify(repository).deleteById(1L);
    }
}
