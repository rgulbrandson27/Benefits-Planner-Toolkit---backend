package com.raina.benefits.api.entity;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true) //Lombok annotation that handles how equality checking works when your entity extends another class.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "monthly_work_status")
public class MonthlyWorkStatus extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "scenario_id", nullable = false)
    private Scenario scenario;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer month;

    // Earnings
    private BigDecimal earningsAmount;

    public enum EarningsCategory {
        BELOW_TWP,
        ABOVE_TWP,
        ABOVE_SGA
    }

    @Enumerated(EnumType.STRING)
    private EarningsCategory earningsCategory;

    // Work incentive month numbers
    private Integer twpMonthNumber;   // 1-9 or null
    private Integer epeMonthNumber;   // 1-36 or null
    private Integer graceMonthNumber; // 1-3 or null

    // Medicare status
    public enum MedicareStatus {
        MEDICARE_BEGAN,
        MEDICARE_BEGINS,
        EPMC_BEGINS,
        EPMC_ENDS
    }

    @Enumerated(EnumType.STRING)
    private MedicareStatus medicareStatus;

    // Benefit status
    public enum BenefitStatus {
        BENEFIT_SUSPENDED,
        BENEFIT_TERMINATED,
        BENEFIT_RESTARTED
    }

    @Enumerated(EnumType.STRING)
    private BenefitStatus benefitStatus;
}