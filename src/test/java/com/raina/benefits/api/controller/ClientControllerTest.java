package com.raina.benefits.api.controller;

import com.raina.benefits.api.entity.Client;
import com.raina.benefits.api.entity.Employee;
import com.raina.benefits.api.repository.ClientRepository;
import com.raina.benefits.api.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientControllerTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void shouldCreateClientWithNameIdDate() {
        // Given
        Client client = new Client();
        client.setOrgAssignedId("AAA111");
        client.setFirstName("Barbara");
        client.setLastName("Beneficiary");

        // When
        Client saved = clientRepository.save(client);

        // Then
        assertNotNull(saved.getId());
        assertEquals("AAA111", saved.getOrgAssignedId());
        assertEquals("Barbara", saved.getFirstName());
        assertEquals("Beneficiary", saved.getLastName());
    }

    @Test
    void shouldUpdateClientPrimaryWorker() {
        // Given
        Client client = new Client();
        client.setOrgAssignedId("EEE555");
        client.setFirstName("Frank");
        client.setLastName("Filer");
        Client saved = clientRepository.save(client);

        // When
        Employee worker = employeeRepository.findById(27L).orElseThrow();
        saved.setPrimaryWorker(worker);
        Client updated = clientRepository.save(saved);

        // Then
        assertNotNull(updated.getPrimaryWorker());
        assertEquals(27L, updated.getPrimaryWorker().getId());
    }

}