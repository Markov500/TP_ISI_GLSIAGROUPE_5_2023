package com.example.egaAPI.Services;

import com.example.egaAPI.Entity.Compte;
import com.example.egaAPI.Entity.Client;
import com.example.egaAPI.Repository.CompteRepository;
import com.example.egaAPI.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService {
    private final ClientRepository clientRepository;
    private final CompteRepository compteRepository;

    @Autowired
    public CompteService(ClientRepository clientRepository, CompteRepository compteRepository) {
        this.clientRepository = clientRepository;
        this.compteRepository = compteRepository;
    }

    // Opération de création d'un compte
    public Compte createCompte(Compte compte) {
        Client client = compte.getClient();
        Integer clientId = client.getId();
        if (clientId == null || !clientRepository.existsById(clientId)) {
            // Client does not exist, create new client
            client = clientRepository.save(client);
        } else {
            // Client already exists, get client from database
            client = clientRepository.getReferenceById(clientId);
        }
        Compte realCompte = new Compte(compte.getTypeCompte(), client);
        return compteRepository.save(realCompte);
    }

    // Opération de lecture d'un compte par ID(numCompte)
    public Optional<Compte> getCompteById(String numCompte) {
        return compteRepository.findById(numCompte);
    }

    // Opération de lecture de tous les comptes
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    // Opération de suppression d'un compte
    public void deleteCompte(String numCompte) {
        compteRepository.deleteById(numCompte);
    }
}