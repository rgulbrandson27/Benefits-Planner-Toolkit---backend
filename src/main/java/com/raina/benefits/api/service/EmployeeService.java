package com.raina.benefits.api.service;

import com.raina.benefits.api.entity.Employee;
import com.raina.benefits.api.entity.ProgramType;
import com.raina.benefits.api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Create/Update
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Read - Get by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Read - Get by email
    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    // Read - Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Read - Get employees by program type
    public List<Employee> getEmployeesByProgramType(ProgramType programType) {
        return employeeRepository.findByProgramType(programType);
    }

    // Search by last name
    public List<Employee> searchByLastName(String lastName) {
        return employeeRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    // Search by full name
    public List<Employee> searchByFullName(String firstName, String lastName) {
        return employeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    // Validation - Check if email exists
    public boolean emailExists(String email) {
        return employeeRepository.existsByEmail(email);
    }

    // Delete
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Check if employee exists
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean employeeExists(Long id) {
        return employeeRepository.existsById(id);
    }
}