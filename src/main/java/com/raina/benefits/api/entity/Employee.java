package com.raina.benefits.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @Enumerated(EnumType.STRING)
    private ProgramType programType;

    private Long organizationId;  // Which organization they work for
}

