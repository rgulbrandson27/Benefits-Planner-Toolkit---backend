
package com.raina.benefits.api.entity;

import jakarta.persistence.*;
        import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "scenarios")
public class Scenario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;

    private String description;

    private String status;

    @Column(columnDefinition = "TEXT")
    private String workIncentivesData;

    @Column(columnDefinition = "TEXT")
    private String earningsData;    // ← JSON of EarningsData (nullable)

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}