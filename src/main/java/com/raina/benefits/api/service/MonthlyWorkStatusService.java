package com.raina.benefits.api.service;

import com.raina.benefits.api.dto.MonthlyWorkStatusDTO;
import com.raina.benefits.api.entity.MonthlyWorkStatus;
import com.raina.benefits.api.entity.MonthlyWorkStatus.EarningsCategory;
import com.raina.benefits.api.entity.MonthlyWorkStatus.MedicareStatus;
import com.raina.benefits.api.entity.Scenario;
import com.raina.benefits.api.repository.MonthlyWorkStatusRepository;
import com.raina.benefits.api.repository.ScenarioRepository;
import com.raina.benefits.api.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MonthlyWorkStatusService {

    private final MonthlyWorkStatusRepository monthlyWorkStatusRepository;
    private final ScenarioRepository scenarioRepository;

    // Get months for grid display (with year range)
    public List<MonthlyWorkStatusDTO> getMonthsForScenario(Long scenarioId, Integer startYear, Integer endYear) {
        return monthlyWorkStatusRepository
                .findByScenarioIdAndYearBetweenOrderByYearAscMonthAsc(scenarioId, startYear, endYear)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get ALL months for a scenario
    public List<MonthlyWorkStatusDTO> getAllMonthsForScenario(Long scenarioId) {
        return monthlyWorkStatusRepository
                .findByScenarioIdOrderByYearAscMonthAsc(scenarioId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get TWP months only (for export/reports)
    public List<MonthlyWorkStatusDTO> getTWPMonthsForScenario(Long scenarioId) {
        return monthlyWorkStatusRepository
                .findByScenarioIdAndTwpMonthNumberIsNotNullOrderByYearAscMonthAsc(scenarioId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Batch save/update monthly work statuses
    @Transactional
    public List<MonthlyWorkStatusDTO> saveMonthlyWorkStatuses(Long scenarioId, List<MonthlyWorkStatusDTO> dtos) {
        Scenario scenario = scenarioRepository.findById(scenarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Scenario not found with id: " + scenarioId));

        // Delete existing records for this scenario
        monthlyWorkStatusRepository.deleteByScenarioId(scenarioId);

        // Insert new records (only non-empty months)
        List<MonthlyWorkStatus> entities = dtos.stream()
                .filter(this::hasContent)
                .map(dto -> convertToEntity(dto, scenario))
                .collect(Collectors.toList());

        List<MonthlyWorkStatus> saved = monthlyWorkStatusRepository.saveAll(entities);

        return saved.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Check if a month has any content
    private boolean hasContent(MonthlyWorkStatusDTO dto) {
        return dto.getTwpMonthNumber() != null ||
                dto.getEpeMonthNumber() != null ||
                dto.getGraceMonthNumber() != null ||
                dto.getEarningsAmount() != null ||
                dto.getEarningsCategory() != null ||
                dto.getMedicareStatus() != null;
    }

    // Convert entity to DTO
    private MonthlyWorkStatusDTO convertToDTO(MonthlyWorkStatus entity) {
        return MonthlyWorkStatusDTO.builder()
                .id(entity.getId())
                .year(entity.getYear())
                .month(entity.getMonth())
                .earningsAmount(entity.getEarningsAmount())
                .earningsCategory(entity.getEarningsCategory() != null ?
                        entity.getEarningsCategory().name() : null)
                .twpMonthNumber(entity.getTwpMonthNumber())
                .epeMonthNumber(entity.getEpeMonthNumber())
                .graceMonthNumber(entity.getGraceMonthNumber())
                .medicareStatus(entity.getMedicareStatus() != null ?
                        entity.getMedicareStatus().name() : null)
                .build();
    }

    // Convert DTO to entity
    private MonthlyWorkStatus convertToEntity(MonthlyWorkStatusDTO dto, Scenario scenario) {
        return MonthlyWorkStatus.builder()
                .scenario(scenario)
                .year(dto.getYear())
                .month(dto.getMonth())
                .earningsAmount(dto.getEarningsAmount())
                .earningsCategory(dto.getEarningsCategory() != null ?
                        EarningsCategory.valueOf(dto.getEarningsCategory()) : null)
                .twpMonthNumber(dto.getTwpMonthNumber())
                .epeMonthNumber(dto.getEpeMonthNumber())
                .graceMonthNumber(dto.getGraceMonthNumber())
                .medicareStatus(dto.getMedicareStatus() != null ?
                        MedicareStatus.valueOf(dto.getMedicareStatus()) : null)
                .build();
    }
}