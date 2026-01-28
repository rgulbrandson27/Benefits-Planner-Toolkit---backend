package com.raina.benefits.api.repository;

import com.raina.benefits.api.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    // Find by organization name
    Optional<Organization> findByName(String name);

    // Find by abbreviation (e.g., "ESN" for Easterseals Nebraska)
    Optional<Organization> findByAbbreviation(String abbreviation);

    // Check if name already exists
    boolean existsByName(String name);

    // Check if abbreviation already exists
    boolean existsByAbbreviation(String abbreviation);
}