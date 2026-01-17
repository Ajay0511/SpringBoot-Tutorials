package com.example.employeeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;

/*
1️⃣ Why interface and not class?
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
Spring Data JPA does not require you to write an implementation class.
By declaring it as an interface and extending JpaRepository, Spring automatically provides the implementation at runtime.
You only need to define custom query methods (optional). Spring generates all standard CRUD methods (save, findById, findAll, deleteById) for you.
If you wrote a class instead, Spring wouldn’t know how to provide the implementation automatically — you’d have to implement all CRUD methods yourself.
✅ Takeaway: Interfaces + JpaRepository = automatic CRUD implementation.


2️⃣ Why <Employee, Long>?
JpaRepository<T, ID> is a generic interface, where:
T = Entity type:
Here, Employee is the entity this repository manages.
It tells Spring Data which table/entity you want to perform operations on.
ID = Primary key type:
Here, Long is the type of the primary key (@Id) in your Employee entity.
Spring uses this to know the type of the id when performing operations like findById(Long id) or deleteById(Long id).
*/


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // public String getEmployee(){
    //     return "Employee from Employee Repository";
    // }
    List<Employee> findByDepartment(String department);

    //JPQL
    @Query("SELECT e from Employee e")  //jpql does not use table name, but entity name so employee give error
    List<Employee> findAllEmployeesJPQL();

    @Query("select e from Employee e where e.department = :department")
    List<Employee> findByDepartmentJPQL(@Param("department") String department);

    @Query("select e from Employee e where e.salary > :salary")
    List<Employee> findHigherSalary(@Param("salary") double salary);

    @Query("select new com.example.employeeservice.dto.EmployeeDto(e.id, e.name, e.department) FROM Employee e")
    public List<EmployeeDto> getEmployeeDtos();


    //Native Queries
    @Query(value = "select * from employees", nativeQuery = true)
    List<Employee> getAllEmployeesNative();

    @Query(value = "select * from employees where department = :department", nativeQuery =  true)
    List<Employee> getEmployeeByDepartmentNative(@Param("department") String department);

    @Query(value = "select * from employees where salary > ?1", nativeQuery = true)
    List<Employee> getEmployeesByHighSalaryNative(double salary);

    @Query(value = "SELECT name, department FROM employees", nativeQuery = true)
    List<Object[]> fetchNameAndDepartmentNative();

    @Query(value = "SELECT * FROM employees ORDER BY salary DESC LIMIT 5", nativeQuery = true)
    List<Employee> findTop5HighestPaid();





}
