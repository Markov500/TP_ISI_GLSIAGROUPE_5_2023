package com.example.egaAPI.Controller;

import com.example.egaAPI.Entity.JsonRespons;
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
//@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    // Opération de création d'un client
    @PostMapping("/clients")
    public ResponseEntity<JsonRespons> createClient(@RequestBody Client client) {
        Client newClient = clientService.createClient(client);

        if (newClient != null) {
           // return ResponseEntity.status(HttpStatus.CREATED).body(newClient);

            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "Client créé avec succès",newClient,1),HttpStatus.OK
            );
        } else {
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "Donnée envoyée invalide",null,0),HttpStatus.BAD_REQUEST
            );
        }
    }

    // Opération de lecture d'un client par ID
    @GetMapping("/clients/{id}")
    public ResponseEntity<JsonRespons> getClientById(@PathVariable("id") Integer id) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isPresent()) {
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "",client,1),HttpStatus.OK
            );
        } else {
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "",client,1),HttpStatus.NOT_FOUND
            );
        }
    }

    // Opération de lecture de tous les clients
    @GetMapping("/clients")
    public ResponseEntity<JsonRespons> getAllClients() {
        List<Client> clients = clientService.getAllClients();
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "",clientService.getAllClients(),clientService.getAllClients().size()),HttpStatus.OK
            );
    }

    // Opération de mise à jour d'un client

    @PutMapping("/clients/{id}")
    public ResponseEntity<JsonRespons> updateClient(@PathVariable Integer id, @RequestBody Client client) {
        Client updatedClient = clientService.updateClient(id, client);
        if (updatedClient != null) {
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "Client modifié avec succes",updatedClient,1),HttpStatus.OK
            );
        } else {
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "Echec de la modification",updatedClient,1),HttpStatus.OK
            );
        }
    }

    // Opération de suppression d'un client

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<JsonRespons> deleteClient(@PathVariable Integer id) {
        try{
            if(clientService.getClientById(id).isPresent()){
                boolean result = clientService.deleteClient(id);
                if (result) {
                    return new ResponseEntity<JsonRespons>(
                            new JsonRespons(HttpStatus.OK.value(), "Suppression réussi",null,0),HttpStatus.OK
                    );
                } else {
                    return new ResponseEntity<JsonRespons>(
                            new JsonRespons(HttpStatus.NOT_FOUND.value(), "Echec de la suppression, client possédant un compte",null,0),HttpStatus.NOT_FOUND
                    );
                }
            }
            else {
                return new ResponseEntity<JsonRespons>(
                        new JsonRespons(HttpStatus.NOT_FOUND.value(), "Echec de la suppression, client introuvable",null,0),HttpStatus.NOT_FOUND
                );
            }

        }
        catch (Exception e){
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "Echec de la suppression",null,0),HttpStatus.OK
            );
        }

    }

}
