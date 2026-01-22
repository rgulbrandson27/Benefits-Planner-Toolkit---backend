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
    private Long id;  // Database ID

    private String clientIdNumber;  // 6-digit reference number (String, not int)

    private Long organizationId;
    private Long primaryWorkerId;

    // Dates (same for all scenarios)
    private LocalDate onsetDate;
    private LocalDate applicationDate;
    private LocalDate entitlementDate;
    private LocalDate medicareStartDate;
}