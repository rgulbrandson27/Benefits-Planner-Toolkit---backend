package com.raina.benefits.api.service;

import com.raina.benefits.api.dto.ScenarioRequest;
import com.raina.benefits.api.dto.ScenarioResponse;
import com.raina.benefits.api.entity.Client;
import com.raina.benefits.api.entity.Employee;
import com.raina.benefits.api.entity.Scenario;
import com.raina.benefits.api.entity.Scenario.ScenarioStatus;
import com.raina.benefits.api.repository.ScenarioRepository;
import com.raina.benefits.api.repository.ClientRepository;
import com.raina.benefits.api.repository.EmployeeRepository;
import com.raina.benefits.api.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    // Create new scenario
    public ScenarioResponse createScenario(ScenarioRequest request, Long employeeId) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Scenario scenario = Scenario.builder()
                .client(client)
                .employee(employee)
                .scenarioStartYear(request.getScenarioStartYear())
                .description(request.getDescription())
                .status(request.getStatus() != null ?
                        ScenarioStatus.valueOf(request.getStatus()) : ScenarioStatus.DRAFT)
                .build();
        Scenario saved = scenarioRepository.save(scenario);
        return convertToResponse(saved);
    }

    // Update existing scenario
    public ScenarioResponse updateScenario(Long id, ScenarioRequest request) {
        Scenario scenario = scenarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scenario not found"));

        scenario.setScenarioStartYear(request.getScenarioStartYear());
        scenario.setDescription(request.getDescription());
        if (request.getStatus() != null) {
            scenario.setStatus(ScenarioStatus.valueOf(request.getStatus()));
        }

        Scenario updated = scenarioRepository.save(scenario);
        return convertToResponse(updated);
    }

    // Get by ID
    public ScenarioResponse getScenarioById(Long id) {
        Scenario scenario = scenarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scenario not found"));
        return convertToResponse(scenario);
    }

    // Get all scenarios
    public List<ScenarioResponse> getAllScenarios() {
        return scenarioRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Get scenarios by client, ordered by most recent
    public List<ScenarioResponse> getScenariosByClientIdOrderedByDate(Long clientId) {
        return scenarioRepository.findByClientIdOrderByCreatedAtDesc(clientId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Get scenarios by employee
    public List<ScenarioResponse> getScenariosByEmployeeId(Long employeeId) {
        return scenarioRepository.findByEmployeeId(employeeId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Get top 10 most recent scenarios by employee
    public List<ScenarioResponse> getTop10ScenariosByEmployeeId(Long employeeId) {
        return scenarioRepository.findTop10ByEmployeeIdOrderByCreatedAtDesc(employeeId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Delete
    public void deleteScenario(Long id) {
        if (!scenarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Scenario not found");
        }
        scenarioRepository.deleteById(id);
    }

    // Convert entity to response DTO
    private ScenarioResponse convertToResponse(Scenario scenario) {
        return new ScenarioResponse(
                scenario.getId(),
                scenario.getClient().getId(),
                scenario.getScenarioStartYear(),
                scenario.getDescription(),
                scenario.getStatus().name(),
                scenario.getCreatedAt(),  // Assuming from BaseEntity
                scenario.getUpdatedAt()   // Assuming from BaseEntity
        );
    }

}