package com.raina.benefits.api.controller;

import com.raina.benefits.api.entity.Employee;
import com.raina.benefits.api.entity.ProgramType;
import com.raina.benefits.api.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        if (employeeService.emailExists(employee.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Email already exists");
        }
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Employee not found"));
    }

    // Get employee by email
    @GetMapping("/email/{email}")
    public Employee getEmployeeByEmail(@PathVariable String email) {
        return employeeService.getEmployeeByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Employee not found"));
    }

    // Get employees by program type
    @GetMapping("/program-type/{programType}")
    public List<Employee> getEmployeesByProgramType(@PathVariable ProgramType programType) {
        return employeeService.getEmployeesByProgramType(programType);
    }

    // Search employees by last name
    @GetMapping("/search/last-name")
    public List<Employee> searchByLastName(@RequestParam String lastName) {
        return employeeService.searchByLastName(lastName);
    }

    // Search employees by full name
    @GetMapping("/search/full-name")
    public List<Employee> searchByFullName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        return employeeService.searchByFullName(firstName, lastName);
    }

    // Update an employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        if (!employeeService.employeeExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        employee.setId(id);
        return employeeService.saveEmployee(employee);
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        if (!employeeService.employeeExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        employeeService.deleteEmployee(id);
    }
}