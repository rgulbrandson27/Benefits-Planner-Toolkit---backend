package com.raina.benefits.api.repository;

import com.raina.benefits.api.entity.Client;
import com.raina.benefits.api.entity.Employee;
import com.raina.benefits.api.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Long> {

    // Find all scenarios for a specific client
    List<Scenario> findByClient(Client client);
    List<Scenario> findByClientId(Long clientId);

    // Find all scenarios created by a specific employee
    List<Scenario> findByEmployee(Employee employee);
    List<Scenario> findByEmployeeId(Long employeeId);

    // Find scenarios by status
    List<Scenario> findByStatus(Scenario.ScenarioStatus status);


    // Find scenarios by client, ordered by most recent first
    List<Scenario> findByClientOrderByCreatedAtDesc(Client client);
    List<Scenario> findByClientIdOrderByCreatedAtDesc(Long clientId);

    // Get the 10 most recent scenarios by a specific employee
    List<Scenario> findTop10ByEmployeeOrderByCreatedAtDesc(Employee employee);
    List<Scenario> findTop10ByEmployeeIdOrderByCreatedAtDesc(Long employeeId);
}