package com.raina.benefits.api.entity;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String abbreviation;

    public String getAbbreviation() {
        return name;
    }

    public void setAbbreviation() {
        this.abbreviation = abbreviation;
    }
}