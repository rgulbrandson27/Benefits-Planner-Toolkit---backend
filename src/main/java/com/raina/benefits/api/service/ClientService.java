package com.raina.benefits.api.service;

import com.raina.benefits.api.entity.Client;
import com.raina.benefits.api.entity.Employee;
import com.raina.benefits.api.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Create/Update
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    // Read - Get by ID
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    // Read - Get by client ID number
    public Optional<Client> getClientByOrgAssignedId(String orgAssignedId) {
        return clientRepository.findByOrgAssignedId(orgAssignedId);
    }

    // Read - Get all clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Read - Get clients by primary worker
    public List<Client> getClientsByPrimaryWorker(Employee employee) {
        return clientRepository.findByPrimaryWorker(employee);
    }

    public List<Client> getClientsByPrimaryWorkerId(Long primaryWorkerId) {
        return clientRepository.findByPrimaryWorkerId(primaryWorkerId);
    }

    // Search by last name
    public List<Client> searchByLastName(String lastName) {
        return clientRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    // Search by full name
    public List<Client> searchByFullName(String firstName, String lastName) {
        return clientRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    // Validation - Check if client ID number exists
    public boolean clientIdNumberExists(String clientIdNumber) {
        return clientRepository.existsByOrgAssignedId(clientIdNumber);
    }

    // Delete
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    // Check if client exists
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean clientExists(Long id) {
        return clientRepository.existsById(id);
    }
}