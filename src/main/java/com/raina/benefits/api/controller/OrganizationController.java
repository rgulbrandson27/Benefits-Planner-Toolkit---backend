package com.raina.benefits.api.controller;

import com.raina.benefits.api.entity.Organization;
import com.raina.benefits.api.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public Organization createOrganization(@RequestBody Organization organization) {
        if (organizationService.nameExists(organization.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Organization name already exists");
        }
        if (organizationService.abbreviationExists(organization.getAbbreviation())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Organization abbreviation already exists");
        }
        return organizationService.saveOrganization(organization);
    }

    @GetMapping
    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable Long id) {
        return organizationService.getOrganizationById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Organization not found"));
    }

    // Get organization by name
    @GetMapping("/name/{name}")
    public Organization getOrganizationByName(@PathVariable String name) {
        return organizationService.getOrganizationByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Organization not found"));
    }

    // Get organization by abbreviation
    @GetMapping("/abbreviation/{abbreviation}")
    public Organization getOrganizationByAbbreviation(@PathVariable String abbreviation) {
        return organizationService.getOrganizationByAbbreviation(abbreviation)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Organization not found"));
    }

    // Update an organization
    @PutMapping("/{id}")
    public Organization updateOrganization(@PathVariable Long id, @RequestBody Organization organization) {
        if (!organizationService.organizationExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization not found");
        }
        organization.setId(id);
        return organizationService.saveOrganization(organization);
    }

    // Delete an organization
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable Long id) {
        if (!organizationService.organizationExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization not found");
        }
        organizationService.deleteOrganization(id);
    }
}