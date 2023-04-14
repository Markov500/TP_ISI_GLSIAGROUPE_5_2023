package com.example.egaAPI.Services;

import com.example.egaAPI.Entity.Client;
import com.example.egaAPI.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Opération de création d'un client
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // Opération de lecture d'un client par ID
    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }

    // Opération de lecture de tous les clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Opération de mise à jour d'un client
    
    /*   public Client updateClient(Integer id, Client client) {
      Optional<Client> optionalClient = clientRepository.findById(id);
      if (optionalClient.isPresent()) {
      client.setId(id);
      return clientRepository.save(client);
      } else {
      return null; // Ou lever une exception appropriée
      }
      } */
    

    // Opération de suppression d'un client
    /* public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    } */
}