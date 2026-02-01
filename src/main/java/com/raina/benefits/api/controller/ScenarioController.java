package com.raina.benefits.api.controller;

import com.raina.benefits.api.entity.Scenario;
import com.raina.benefits.api.service.ScenarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/scenarios")
@RequiredArgsConstructor
public class ScenarioController {

    private final ScenarioService scenarioService;

    @PostMapping
    public Scenario createScenario(@RequestBody Scenario scenario) {
        return scenarioService.saveScenario(scenario);
    }

    @GetMapping
    public List<Scenario> getAllScenarios() {
        return scenarioService.getAllScenarios();
    }

    @GetMapping("/{id}")
    public Scenario getScenarioById(@PathVariable Long id) {
        return scenarioService.getScenarioById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Scenario not found"));
    }

    // Get scenarios by client ID
    @GetMapping("/client/{clientId}")
    public List<Scenario> getScenariosByClientId(@PathVariable Long clientId) {
        return scenarioService.getScenariosByClientId(clientId);
    }

    // Get scenarios by client ID, ordered by most recent
    @GetMapping("/client/{clientId}/recent")
    public List<Scenario> getScenariosByClientIdOrdered(@PathVariable Long clientId) {
        return scenarioService.getScenariosByClientIdOrderedByDate(clientId);
    }

    // Get scenarios by employee ID
    @GetMapping("/employee/{employeeId}")
    public List<Scenario> getScenariosByEmployeeId(@PathVariable Long employeeId) {
        return scenarioService.getScenariosByEmployeeId(employeeId);
    }

    // Get top 10 most recent scenarios by employee ID
    @GetMapping("/employee/{employeeId}/recent")
    public List<Scenario> getTop10ScenariosByEmployeeId(@PathVariable Long employeeId) {
        return scenarioService.getTop10ScenariosByEmployeeId(employeeId);
    }

    // Get scenarios by status
    @GetMapping("/status/{status}")
    public List<Scenario> getScenariosByStatus(@PathVariable String status) {
        return scenarioService.getScenariosByStatus(status);
    }

    // Update a scenario
    @PutMapping("/{id}")
    public Scenario updateScenario(@PathVariable Long id, @RequestBody Scenario scenario) {
        if (!scenarioService.scenarioExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scenario not found");
        }
        scenario.setId(id);
        return scenarioService.saveScenario(scenario);
    }

    // Delete a scenario
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScenario(@PathVariable Long id) {
        if (!scenarioService.scenarioExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scenario not found");
        }
        scenarioService.deleteScenario(id);
    }
}