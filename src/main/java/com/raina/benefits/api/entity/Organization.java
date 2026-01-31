package com.raina.benefits.api.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "organizations")
public class Organization extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String abbreviation;

    // Relationships - Organization has many employees
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    // Relationships - Organization has many clients
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Client> clients = new ArrayList<>();
}