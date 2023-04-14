package com.example.egaAPI.Controller;

import com.example.egaAPI.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.egaAPI.Entity.Client;
import com.example.egaAPI.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Opération de création d'un client
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client newClient = clientService.createClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    // Opération de lecture d'un client par ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Opération de lecture de tous les clients
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // Opération de mise à jour d'un client
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client client) {
        Client updatedClient = clientService.updateClient(id, client);
        if (updatedClient != null) {
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Opération de suppression d'un client
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}