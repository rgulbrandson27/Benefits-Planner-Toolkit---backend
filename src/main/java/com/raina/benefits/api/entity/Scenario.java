package com.raina.benefits.api.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder  // <-- Need this!
@NoArgsConstructor
@AllArgsConstructor
@Entity
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

    public enum ScenarioStatus {
        DRAFT,
        COMPLETED,
        ARCHIVED
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScenarioStatus status = ScenarioStatus.DRAFT;
}
//    // OLD
//    Stored as JSON
//    @Column(columnDefinition = "TEXT")
//    private String workIncentivesData;
//
//    @Column(columnDefinition = "TEXT")
//    private String earningsData;

