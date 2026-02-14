package com.raina.benefits.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyWorkStatusDTO {
    private Long id;
    private Integer year;
    private Integer month;

    // Earnings
    private BigDecimal earningsAmount;
    private String earningsCategory;  // "BELOW_TWP", "ABOVE_TWP", "ABOVE_SGA"

    // Work incentive month numbers
    private Integer twpMonthNumber;   // 1-9 or null
    private Integer epeMonthNumber;   // 1-36 or null
    private Integer graceMonthNumber; // 1-3 or null

    // Medicare status
    private String medicareStatus;    // "MEDICARE_BEGAN", "MEDICARE_BEGINS", etc. or null
}