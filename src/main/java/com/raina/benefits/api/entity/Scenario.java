package com.raina.benefits.api.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "scenarios")
public class Scenario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationship - Scenario belongs to one Client (required)
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // Relationship - Scenario created by one Employee (required)
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(length = 500)
    private String description;

    @Column(length = 50)
    private String status;  // e.g., "DRAFT", "ACTIVE", "ARCHIVED"

    // Complex SSDI calculation data stored as JSON
    @Column(columnDefinition = "TEXT")
    private String workIncentivesData;  // TWP months, EPE periods, etc.

    @Column(columnDefinition = "TEXT")
    private String earningsData;  // Monthly earnings array as JSON

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Automatically set timestamps
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}