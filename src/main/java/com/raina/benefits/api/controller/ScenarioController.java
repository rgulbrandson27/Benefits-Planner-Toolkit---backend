package com.raina.benefits.api.controller;

import com.raina.benefits.api.dto.ScenarioRequest;
import com.raina.benefits.api.dto.ScenarioResponse;
import com.raina.benefits.api.service.ScenarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scenarios")
@RequiredArgsConstructor
public class ScenarioController {

    private final ScenarioService scenarioService;

    // Create a new scenario
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScenarioResponse createScenario(
            @RequestBody ScenarioRequest request,
            @RequestHeader("Employee-Id") Long employeeId) {  // Or get from auth token
        return scenarioService.createScenario(request, employeeId);
    }

    // Get all scenarios
    @GetMapping
    public List<ScenarioResponse> getAllScenarios() {
        return scenarioService.getAllScenarios();
    }

    // Get scenario by ID
    @GetMapping("/{id}")
    public ScenarioResponse getScenarioById(@PathVariable Long id) {
        return scenarioService.getScenarioById(id);
    }

    // Get scenarios by client ID, ordered by most recent
    @GetMapping("/client/{clientId}")
    public List<ScenarioResponse> getScenariosByClientId(@PathVariable Long clientId) {
        return scenarioService.getScenariosByClientIdOrderedByDate(clientId);
    }

    // Get scenarios by employee ID
    @GetMapping("/employee/{employeeId}")
    public List<ScenarioResponse> getScenariosByEmployeeId(@PathVariable Long employeeId) {
        return scenarioService.getScenariosByEmployeeId(employeeId);
    }

    // Get top 10 most recent scenarios by employee ID
    @GetMapping("/employee/{employeeId}/recent")
    public List<ScenarioResponse> getTop10ScenariosByEmployeeId(@PathVariable Long employeeId) {
        return scenarioService.getTop10ScenariosByEmployeeId(employeeId);
    }

    // Update a scenario
    @PutMapping("/{id}")
    public ScenarioResponse updateScenario(
            @PathVariable Long id,
            @RequestBody ScenarioRequest request) {
        return scenarioService.updateScenario(id, request);
    }

    // Delete a scenario
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScenario(@PathVariable Long id) {
        scenarioService.deleteScenario(id);
    }
}