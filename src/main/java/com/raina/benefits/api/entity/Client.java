package com.raina.benefits.api.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 6)
    private String clientIdNumber;  // 6-digit business ID

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    // Relationship - Client belongs to one Organization
    @ManyToOne(optional = true)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    // Relationship - Client has one primary worker (Employee)
    @ManyToOne (optional = true)
    @JoinColumn(name = "primary_worker_id")
    private Employee primaryWorker;

    // SSDI-specific dates
    @Column(nullable = true)
    private LocalDate onsetDate;

    private LocalDate applicationDate;

    private LocalDate entitlementDate;

    private LocalDate medicareStartDate;
}