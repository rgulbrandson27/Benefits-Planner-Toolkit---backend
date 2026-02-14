package com.raina.benefits.api.service;

import com.raina.benefits.api.entity.Client;
import com.raina.benefits.api.entity.Employee;
import com.raina.benefits.api.entity.Scenario;
import com.raina.benefits.api.repository.ScenarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;

    public ScenarioService(ScenarioRepository scenarioRepository) {
        this.scenarioRepository = scenarioRepository;
    }

    // Create/Update
    public Scenario saveScenario(Scenario scenario) {
        return scenarioRepository.save(scenario);
    }

    // Read - Get by ID
    public Optional<Scenario> getScenarioById(Long id) {
        return scenarioRepository.findById(id);
    }

    // Read - Get all scenarios
    public List<Scenario> getAllScenarios() {
        return scenarioRepository.findAll();
    }

    // Read - Get scenarios by client
    public List<Scenario> getScenariosByClient(Client client) {
        return scenarioRepository.findByClient(client);
    }

    public List<Scenario> getScenariosByClientId(Long clientId) {
        return scenarioRepository.findByClientId(clientId);
    }

    // Read - Get scenarios by client, ordered by most recent
    public List<Scenario> getScenariosByClientOrderedByDate(Client client) {
        return scenarioRepository.findByClientOrderByCreatedAtDesc(client);
    }

    public List<Scenario> getScenariosByClientIdOrderedByDate(Long clientId) {
        return scenarioRepository.findByClientIdOrderByCreatedAtDesc(clientId);
    }

    // Read - Get scenarios by employee
    public List<Scenario> getScenariosByEmployee(Employee employee) {
        return scenarioRepository.findByEmployee(employee);
    }

    public List<Scenario> getScenariosByEmployeeId(Long employeeId) {
        return scenarioRepository.findByEmployeeId(employeeId);
    }

    // Read - Get top 10 most recent scenarios by employee
    public List<Scenario> getTop10ScenariosByEmployee(Employee employee) {
        return scenarioRepository.findTop10ByEmployeeOrderByCreatedAtDesc(employee);
    }

    public List<Scenario> getTop10ScenariosByEmployeeId(Long employeeId) {
        return scenarioRepository.findTop10ByEmployeeIdOrderByCreatedAtDesc(employeeId);
    }

    // Delete
    public void deleteScenario(Long id) {
        scenarioRepository.deleteById(id);
    }

    // Check if scenario exists
    public boolean scenarioExists(Long id) {
        return scenarioRepository.existsById(id);
    }
}