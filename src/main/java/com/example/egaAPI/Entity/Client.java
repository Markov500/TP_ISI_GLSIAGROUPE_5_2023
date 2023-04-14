package com.example.egaAPI.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String sexe;
    private String adresse;
    private String telephone;
    private String couriel;

    public Client() {
    }

    public Client(String nom, String prenom, LocalDate dateNaissance, String sexe, String adresse, String telephone,
            String couriel) {
        List<String> sexes = List.of("masculin", "feminin");
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        if (sexes.contains(sexe.toLowerCase())) {
            this.sexe = sexe.toLowerCase();
        }

        this.adresse = adresse;
        this.telephone = telephone;
        this.couriel = couriel;
    }

    public void setId(Integer id2) {
    }
}
