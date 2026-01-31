package com.raina.benefits.api.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "scenarios")
public class Scenario extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = true)
    private Integer scenarioStartYear; // the first row in the chart of 13 total years listed

    @Column(length = 500)
    private String description;

    @Column(length = 50)
    private String status;  // e.g., "DRAFT", "COMPLETED", "ARCHIVED"

    // Stored as JSON
    @Column(columnDefinition = "TEXT")
    private String workIncentivesData;

    @Column(columnDefinition = "TEXT")
    private String earningsData;

}