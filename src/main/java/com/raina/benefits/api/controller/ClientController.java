package com.raina.benefits.api.controller;

import com.raina.benefits.api.entity.Client;
import com.raina.benefits.api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor  // Add this - Lombok generates the constructor for you
public class ClientController {

    private final ClientRepository clientRepository;  // Remove @Autowired, add final

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        if (clientRepository.existsByClientIdNumber(client.getClientIdNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Client ID already exists");
        }
        return clientRepository.save(client);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Client not found"));
    }
}