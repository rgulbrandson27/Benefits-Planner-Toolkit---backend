package com.raina.benefits.api.service;

import com.raina.benefits.api.entity.Organization;
import com.raina.benefits.api.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    // Create/Update
    public Organization saveOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    // Read - Get by ID
    public Optional<Organization> getOrganizationById(Long id) {
        return organizationRepository.findById(id);
    }

    // Read - Get by name
    public Optional<Organization> getOrganizationByName(String name) {
        return organizationRepository.findByName(name);
    }

    // Read - Get by abbreviation
    public Optional<Organization> getOrganizationByAbbreviation(String abbreviation) {
        return organizationRepository.findByAbbreviation(abbreviation);
    }

    // Read - Get all organizations
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    // Validation - Check if name exists
    public boolean nameExists(String name) {
        return organizationRepository.existsByName(name);
    }

    // Validation - Check if abbreviation exists
    public boolean abbreviationExists(String abbreviation) {
        return organizationRepository.existsByAbbreviation(abbreviation);
    }

    // Delete
    public void deleteOrganization(Long id) {
        organizationRepository.deleteById(id);
    }

    // Check if organization exists
    public boolean organizationExists(Long id) {
        return organizationRepository.existsById(id);
    }
}