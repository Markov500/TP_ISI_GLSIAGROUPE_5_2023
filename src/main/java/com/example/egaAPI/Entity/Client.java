package com.example.egaAPI.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("prenom")
    private String prenom;
    //@JsonProperty("dateNaissance")
    private LocalDate dateNaissance;
    @JsonProperty("sexe")
    private String sexe;
    @JsonProperty("adresse")
    private String adresse;
    @JsonProperty("telephone")
    private String telephone;
    @JsonProperty("couriel")
    private String couriel;
    @JsonProperty("nationalite")
    private String nationalite;




    public Client(String nom, String prenom, LocalDate dateNaissance, String sexe, String adresse, String telephone, String couriel, String nationalite) {
        List<String> sexes = List.of("masculin","feminin");
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        if (sexes.contains(sexe.toLowerCase())) {
            this.sexe = sexe.toLowerCase();
        }

        this.adresse = adresse;
        this.telephone = telephone;
        this.couriel = couriel;
        this.nationalite = nationalite;
    }


}
