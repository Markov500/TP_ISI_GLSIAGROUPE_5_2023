package com.example.egaAPI.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Data
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
        this.solde= 0.0;
        this.client = client;
        this.solde=0.0;


    }

    public Compte() {
        this.numCompte=_genererNumCompte();
        this.solde=0.0;
        this.dateCreation = LocalDate.now();
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
        String  yaer= String.valueOf(LocalDate.now().getYear());
        return sb.toString() +yaer ;

    }

    //Les méthodes publics
    public void depot(double montant)
    {
        this.solde += montant;
    }

    public boolean retrait(double montant)
    {
        if (montant >= 0 && solde >= montant)
        {
            this.solde -= montant;
            return true;
        }
        return false;
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


