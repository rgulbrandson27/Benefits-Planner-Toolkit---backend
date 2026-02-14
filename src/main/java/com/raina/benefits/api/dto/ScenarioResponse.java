package com.raina.benefits.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioResponse {
    private Long id;
    private Long clientId;
    private Integer scenarioStartYear;
    private String description;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;
}