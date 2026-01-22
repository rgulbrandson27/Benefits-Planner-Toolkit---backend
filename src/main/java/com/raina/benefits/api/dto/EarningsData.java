package com.raina.benefits.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EarningsData {
    private List<EarningsEntry> entries;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EarningsEntry {
        private String month;              // "MM/YYYY"
        private String status;             // "BELOW_TWP", "ABOVE_TWP", "ABOVE_SGA"
        private BigDecimal grossEarnings;
    }
}