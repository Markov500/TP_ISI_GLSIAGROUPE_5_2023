package com.example.egaAPI.Services;

import com.example.egaAPI.Entity.Compte;
import com.example.egaAPI.Repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService {
    @Autowired
    private CompteRepository compteRepository;

    // Opération de création d'un compte
    public Compte createCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    // Opération de lecture d'un compte par ID
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