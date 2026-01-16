package com.example.employeeservice.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.dto.EmployeeDto;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/hateos/v2")
public class HateoasTestController {

    private List<EmployeeDto> employees = List.of(
            new EmployeeDto(1L, "Ajay", "IT"),
            new EmployeeDto(2L, "Sameer", "HR")
    );

    // GET /api/v2/employees with HATEOAS links
    @GetMapping("/employees")
    public CollectionModel<EntityModel<EmployeeDto>> getAllEmployees() {

        // Convert each EmployeeDto to an EntityModel with links
        List<EntityModel<EmployeeDto>> employeeModels = employees.stream()
                .map(employee -> EntityModel.of(employee,
                        linkTo(methodOn(HateoasTestController.class).getEmployeeById(employee.getId())).withSelfRel(),
                        linkTo(methodOn(HateoasTestController.class).getAllEmployees()).withRel("all-employees")
                ))
                .toList();

        // Wrap the list into CollectionModel to add top-level links if needed
        return CollectionModel.of(employeeModels,
                linkTo(methodOn(HateoasTestController.class).getAllEmployees()).withSelfRel());
    }
    /*
    {
    "_embedded": {
        "employeeDtoList": [
            {
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/hateos/v2/getById/1"
                    },
                    "all-employees": {
                        "href": "http://localhost:8080/api/hateos/v2/employees"
                    }
                },
                "id": 1,
                "name": "Ajay",
                "department": "IT"
            },
            {
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/hateos/v2/getById/2"
                    },
                    "all-employees": {
                        "href": "http://localhost:8080/api/hateos/v2/employees"
                    }
                },
                "id": 2,
                "name": "Sameer",
                "department": "HR"
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/hateos/v2/employees"
        }
    }
}
    */

    // GET /api/v2/getById/{id}
    @GetMapping("/getById/{id}")
    public EntityModel<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        EmployeeDto employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return EntityModel.of(employee,
                linkTo(methodOn(HateoasTestController.class).getEmployeeById(id)).withSelfRel(),
                linkTo(methodOn(HateoasTestController.class).getAllEmployees()).withRel("all-employees"));
    }
}
