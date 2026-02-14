package com.raina.benefits.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "monthly_work_status",
        uniqueConstraints = @UniqueConstraint(columnNames = {"scenario_id", "year", "month"})
)
public class MonthlyWorkStatus extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenario_id", nullable = false)
    private Scenario scenario;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer month;  // 1-12

    //For later
    @Column(precision = 10, scale = 2)
    private BigDecimal earningsAmount;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private EarningsCategory earningsCategory;

    public enum EarningsCategory {
        BELOW_TWP,
        ABOVE_TWP,
        ABOVE_SGA
    }

    @Column
    private Integer twpMonthNumber;

    @Column
    private Integer epeMonthNumber;

    @Column
    private Integer graceMonthNumber;
    @Enumerated(EnumType.STRING)

    @Column(length = 50)
    private MedicareStatus medicareStatus;  // nullable, defaults to null

    public enum MedicareStatus {
        MEDICARE_BEGAN,
        MEDICARE_BEGINS,
        MEDICARE_ENDS,
        EPMC_BEGINS,
        EPMC_ENDS
    }
}