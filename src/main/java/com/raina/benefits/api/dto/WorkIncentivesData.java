package com.raina.benefits.api.dto;

import lombok.Data;
import java.util.List;

@Data
public class WorkIncentivesData {
    private List<EarningsEntry> earnings;
    private List<TWPMonth> twpMonths;
    private EPEPeriod epe;
    private CessationAndGrace cessationAndGrace;

    @Data
    public static class EarningsEntry {
        private String month;     // "MM/YYYY"
        private String status;    // "BELOW_TWP", "ABOVE_TWP", "ABOVE_SGA"
    }

    @Data
    public static class TWPMonth {
        private Integer twpNumber;  // 1-9
        private String month;       // "MM/YYYY" or null
        private String type;        // "actual", "projected", or null
    }

    @Data
    public static class EPEPeriod {
        private String beginMonth;  // "MM/YYYY"
        private String endMonth;    // "MM/YYYY"
    }

    @Data
    public static class CessationAndGrace {
        private String cessationMonth;
        private String graceMonth1;
        private String graceMonth2;
    }
}