package com.example.egaAPI.Controller;

import com.example.egaAPI.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.egaAPI.Entity.Client;
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
        if (newClient != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Opération de lecture d'un client par ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Opération de lecture de tous les clients
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        if (!clients.isEmpty()) {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Opération de mise à jour d'un client

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client client) {
        Client updatedClient = clientService.updateClient(id, client);
        if (updatedClient != null) {
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Opération de suppression d'un client

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable Integer id) {
        boolean result = clientService.deleteClient(id);
        if (result) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
