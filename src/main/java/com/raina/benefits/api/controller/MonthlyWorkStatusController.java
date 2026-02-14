package com.raina.benefits.api.controller;

import com.raina.benefits.api.dto.MonthlyWorkStatusDTO;
import com.raina.benefits.api.service.MonthlyWorkStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scenarios/{scenarioId}/monthly-work-status")
@RequiredArgsConstructor
public class MonthlyWorkStatusController {

    private final MonthlyWorkStatusService monthlyWorkStatusService;

    // GET /api/scenarios/123/monthly-work-status?startYear=2020&endYear=2032
    @GetMapping
    public ResponseEntity<List<MonthlyWorkStatusDTO>> getMonthlyWorkStatuses(
            @PathVariable Long scenarioId,
            @RequestParam(required = false) Integer startYear,
            @RequestParam(required = false) Integer endYear) {

        List<MonthlyWorkStatusDTO> months;

        if (startYear != null && endYear != null) {
            months = monthlyWorkStatusService.getMonthsForScenario(scenarioId, startYear, endYear);
        } else {
            months = monthlyWorkStatusService.getAllMonthsForScenario(scenarioId);
        }

        return ResponseEntity.ok(months);
    }

    // GET /api/scenarios/123/monthly-work-status/twp
    @GetMapping("/twp")
    public ResponseEntity<List<MonthlyWorkStatusDTO>> getTWPMonths(@PathVariable Long scenarioId) {
        return ResponseEntity.ok(monthlyWorkStatusService.getTWPMonthsForScenario(scenarioId));
    }

    // PUT /api/scenarios/123/monthly-work-status
    @PutMapping
    public ResponseEntity<List<MonthlyWorkStatusDTO>> saveMonthlyWorkStatuses(
            @PathVariable Long scenarioId,
            @RequestBody List<MonthlyWorkStatusDTO> monthlyStatuses) {

        List<MonthlyWorkStatusDTO> saved = monthlyWorkStatusService.saveMonthlyWorkStatuses(
                scenarioId, monthlyStatuses
        );

        return ResponseEntity.ok(saved);
    }
}