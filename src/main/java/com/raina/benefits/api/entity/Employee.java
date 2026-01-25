package com.raina.benefits.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProgramType programType;

    // Relationship - Employee belongs to one Organization
    @ManyToOne(optional = false)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    // Relationship - Employee can be primary worker for many clients
    @OneToMany(mappedBy = "primaryWorker")
    private List<Client> primaryClients = new ArrayList<>();

    // Relationship - Employee can create many scenarios
    @OneToMany(mappedBy = "employee")
    private List<Scenario> createdScenarios = new ArrayList<>();
}