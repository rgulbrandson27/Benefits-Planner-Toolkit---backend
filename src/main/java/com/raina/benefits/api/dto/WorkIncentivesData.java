package com.raina.benefits.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkIncentivesData {
    private List<TWPMonth> twpMonths;
    private EPEPeriod epe;
    private CessationAndGrace cessationAndGrace;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TWPMonth {
        private Integer twpNumber;  // 1-9
        private String month;       // "MM/YYYY" or null
        private String type;        // "actual", "projected", or null
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EPEPeriod {
        private String beginMonth;  // "MM/YYYY"
        private String endMonth;    // "MM/YYYY"
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CessationAndGrace {
        private String cessationMonth;
        private String graceMonth1;
        private String graceMonth2;
    }
}