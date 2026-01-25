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
    @ManyToOne(optional = false)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    // Relationship - Client has one primary worker (Employee)
    @ManyToOne
    @JoinColumn(name = "primary_worker_id")
    private Employee primaryWorker;

    // SSDI-specific dates
    @Column(nullable = false)
    private LocalDate onsetDate;

    private LocalDate applicationDate;

    @Column(nullable = false)
    private LocalDate entitlementDate;

    private LocalDate medicareStartDate;
}