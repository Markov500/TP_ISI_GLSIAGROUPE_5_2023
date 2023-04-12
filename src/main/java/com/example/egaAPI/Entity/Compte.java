package com.example.egaAPI.Entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "comptes")
public class Compte {
    @Id
    private String numCompte;
    private String typeCompte;
    private LocalDate dateCreation;
    private Double solde;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    public Compte(String typeCompte, Client client)
    {
        List<String> type = List.of("epargne", "courant");
        this.client = client;
        this.dateCreation = LocalDate.now();

        this.numCompte = _genererNumCompte();
        if(type.contains(typeCompte.toLowerCase()))
        {
            this.typeCompte = typeCompte.toLowerCase();
        }

        this.client = client;


    }

    public Compte() {

    }



//Les getters
    public String getNumCompte() {
        return numCompte;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Double getSolde() {
        return solde;
    }

    public Client getClient() {
        return client;
    }



//les setters

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public void setClient(Client client) {
        this.client = client;
    }

//Les méthodes privées
    private String _genererNumCompte()
    {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int randomInt = rand.nextInt(26) + 97;
            char randomChar = (char) randomInt;
            sb.append(randomChar);
        }
        return sb.toString() + this.dateCreation.getYear();

    }

    //Les méthodes publics
    public void depot(double montant)
    {
        this.solde = montant;
    }

    public boolean retrait(double montant)
    {
        if (montant > this.solde)
        {
            return false;
        }
        return true;
    }

    public boolean virement(double montant, Compte compteDestinataire)
    {
        if(this.retrait(montant))
        {
            compteDestinataire.depot(montant);
            return true;
        }
        return false;
    }
}


