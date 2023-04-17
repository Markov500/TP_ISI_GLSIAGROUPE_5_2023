package com.example.egaAPI.Controller;

import com.example.egaAPI.Services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.egaAPI.Entity.Compte;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comptes")
public class CompteController {
    @Autowired
    private CompteService compteService;

    // Opération de création d'un compte
    @PostMapping
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
        Compte newCompte = compteService.createCompte(compte);
        if (newCompte != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newCompte);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Opération de lecture d'un compte par ID(numCompte)
    @GetMapping("/{numCompte}")
    public ResponseEntity<Compte> getCompteById(@PathVariable("numCompte") String numCompte) {
        Optional<Compte> compte = compteService.getCompteById(numCompte);
        if (compte.isPresent()) {
            return new ResponseEntity<>(compte.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Opération de lecture de tous les comptes
    @GetMapping
    public ResponseEntity<List<Compte>> getAllComptes() {
        List<Compte> comptes = compteService.getAllComptes();
        if (!comptes.isEmpty()) {
            return new ResponseEntity<>(comptes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Opération de suppression d'un compte
    @DeleteMapping("/{numCompte}")
    public ResponseEntity<String> deleteCompte(@PathVariable("numCompte") String numCompte) {
        compteService.deleteCompte(numCompte);
        return new ResponseEntity<>("Le compte a été supprimé avec succès", HttpStatus.OK);
    }
}