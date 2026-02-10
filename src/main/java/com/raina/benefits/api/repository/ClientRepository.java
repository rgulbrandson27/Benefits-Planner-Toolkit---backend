package com.raina.benefits.api.repository;

import com.raina.benefits.api.entity.Client;
import com.raina.benefits.api.entity.Employee;
import com.raina.benefits.api.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Find by 6-digit client assigned ID
    Optional<Client> findByOrgAssignedId(String orgAssignedId);

    // Check if client ID number already exists (for validation)
    boolean existsByOrgAssignedId(String clientIdNumber);

    // Find all clients for an organization
    //List<Client> findByOrganization(Organization organization);
    //List<Client> findByOrganizationId(Long organizationId);

    // Find all clients for a specific primary worker
    List<Client> findByPrimaryWorker(Employee employee);
    List<Client> findByPrimaryWorkerId(Long primaryWorkerId);

    // Search by last name (case-insensitive, partial match)
    List<Client> findByLastNameContainingIgnoreCase(String lastName);

    // Search by full name
    List<Client> findByFirstNameAndLastName(String firstName, String lastName);

    List<Client> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
}