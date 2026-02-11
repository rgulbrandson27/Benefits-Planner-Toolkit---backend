package com.raina.benefits.api.controller;

import com.raina.benefits.api.entity.Client;
import com.raina.benefits.api.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        if (clientService.orgAssignedIdExists(client.getOrgAssignedId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Client ID already exists");
        }
        return clientService.saveClient(client);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientByIdOrThrow(id);
    }

    @GetMapping("/org-id/{orgAssignedId}")
    public Client getClientByOrgAssignedId(@PathVariable String orgAssignedId) {
        return clientService.getClientByOrgAssignedId(orgAssignedId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Client not found"));
    }

    @GetMapping("/primary-worker/{workerId}")
    public List<Client> getClientsByPrimaryWorker(@PathVariable Long workerId) {
        return clientService.getClientsByPrimaryWorkerId(workerId);
    }

    @GetMapping("/search/last-name")
    public List<Client> searchByLastName(@RequestParam String lastName) {
        return clientService.searchByLastName(lastName);
    }

    @GetMapping("/search/full-name")
    public List<Client> searchByFullName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        return clientService.searchByFullName(firstName, lastName);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        if (!clientService.clientExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }
        client.setId(id);
        return clientService.saveClient(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}