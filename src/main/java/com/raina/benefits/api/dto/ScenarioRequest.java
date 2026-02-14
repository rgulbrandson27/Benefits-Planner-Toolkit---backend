package com.raina.benefits.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioRequest {
    private Long clientId;
    private Integer scenarioStartYear;  // Added: from your Scenario entity
    private String description;
    private String status;  // "DRAFT", "COMPLETED", "ARCHIVED"
}