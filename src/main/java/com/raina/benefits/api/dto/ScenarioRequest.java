package com.raina.benefits.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioRequest {
    private Long clientId;
    private String description;
    private String status;

    private WorkIncentivesData timeline;    // Timeline tracking
    private EarningsData earnings;          // Earnings information
}
