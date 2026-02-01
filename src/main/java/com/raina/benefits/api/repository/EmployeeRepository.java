package com.raina.benefits.api.repository;

import com.raina.benefits.api.entity.Employee;
import com.raina.benefits.api.entity.Organization;
import com.raina.benefits.api.entity.ProgramType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find by email (for login/authentication)
    Optional<Employee> findByEmail(String email);

    // Check if email already exists (for validation when creating employees)
    boolean existsByEmail(String email);

    // Find all employees in an organization
    //List<Employee> findByOrganization(Organization organization);
   // List<Employee> findByOrganizationId(Long organizationId);

    // Find employees by program type (all WIPA counselors, all TTW specialists, etc.)
    List<Employee> findByProgramType(ProgramType programType);

    // Search by last name (case-insensitive, partial match)
    List<Employee> findByLastNameContainingIgnoreCase(String lastName);

    // Search by full name
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}