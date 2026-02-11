package com.raina.benefits.api.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "clients")
public class Client extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 6-digit ID assigned by worker
    @Column(unique = true, nullable = false, length = 6)
    private String orgAssignedId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToOne(optional = true)
    @JoinColumn(name = "primary_worker_id")
    private Employee primaryWorker;

    // SSDI-specific dates
    @Column(nullable = true)
    private LocalDate onsetDate;

    private LocalDate applicationDate;

    private LocalDate entitlementDate;

    private LocalDate medicareStartDate;
}

//FOR LATER
//@ManyToOne(optional = false)
//@JoinColumn(name = "organization_id", nullable = false)
//private Organization organization;
