package com.raina.benefits.api.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "scenarios")

public class Scenario extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private Integer scenarioStartYear;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private ScenarioStatus status;

    public enum ScenarioStatus {
        DRAFT,
        COMPLETED,
        ARCHIVED
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScenarioStatus scenario_status = ScenarioStatus.DRAFT;

    @OneToMany(mappedBy = "scenario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MonthlyWorkStatus> monthlyWorkStatuses = new ArrayList<>();

}
//    // OLD
//    Stored as JSON
//    @Column(columnDefinition = "TEXT")
//    private String workIncentivesData;
//
//    @Column(columnDefinition = "TEXT")
//    private String earningsData;

