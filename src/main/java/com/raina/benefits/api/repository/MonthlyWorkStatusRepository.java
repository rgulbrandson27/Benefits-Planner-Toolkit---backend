package com.raina.benefits.api.repository;

import com.raina.benefits.api.entity.MonthlyWorkStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonthlyWorkStatusRepository extends JpaRepository<MonthlyWorkStatus, Long> {

    // Get months for grid display (with year range)
    List<MonthlyWorkStatus> findByScenarioIdAndYearBetweenOrderByYearAscMonthAsc(
            Long scenarioId, Integer startYear, Integer endYear
    );

    // Get ALL months for a scenario
    List<MonthlyWorkStatus> findByScenarioIdOrderByYearAscMonthAsc(Long scenarioId);

    // Get TWP months only (for export/reports)
    List<MonthlyWorkStatus> findByScenarioIdAndTwpMonthNumberIsNotNullOrderByYearAscMonthAsc(
            Long scenarioId
    );

    // Find one specific month
    Optional<MonthlyWorkStatus> findByScenarioIdAndYearAndMonth(
            Long scenarioId, Integer year, Integer month
    );

    // Delete all for scenario (for batch save)
    @Transactional
    void deleteByScenarioId(Long scenarioId);
}