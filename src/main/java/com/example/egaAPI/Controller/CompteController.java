package com.example.egaAPI.Controller;

import com.example.egaAPI.Entity.JsonRespons;
import com.example.egaAPI.Services.CompteService;
import com.example.egaAPI.operation.OptCompte;
import com.example.egaAPI.operation.OptVirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.egaAPI.Entity.Compte;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/comptes")
public class CompteController {
    @Autowired
    private CompteService compteService;


    // Opération de création d'un compte
    @PostMapping("/comptes")
    public ResponseEntity<JsonRespons> createCompte(@RequestBody Compte compte) {
        try{
            Compte newCompte = compteService.createCompte(compte);
            if (newCompte != null) {
                return new ResponseEntity<JsonRespons>(
                        new JsonRespons(HttpStatus.OK.value(), "Compte créé avec succès",newCompte,1),HttpStatus.OK
                );
            } else {
                return new ResponseEntity<JsonRespons>(
                        new JsonRespons(HttpStatus.OK.value(), "Echec de la création du compte",null,0),HttpStatus.OK
                );
            }
        }catch (Exception e)
        {
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "Le client n'a pas ete retrouve",null,0),HttpStatus.BAD_REQUEST
            );
        }

    }

    // Opération de lecture d'un compte par ID(numCompte)
    @GetMapping("/comptes/{numCompte}")
    public ResponseEntity<JsonRespons> getCompteById(@PathVariable("numCompte") String numCompte) {
        Optional<Compte> compte = compteService.getCompteById(numCompte);
        if (compte.isPresent()) {
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "",compte,1),HttpStatus.OK
            );
        } else {
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "Aucun compte trouvé",null,1),HttpStatus.OK
            );
        }
    }

    // Opération de lecture de tous les comptes
    @GetMapping("/comptes")
    public ResponseEntity<JsonRespons> getAllComptes() {
        List<Compte> comptes = compteService.getAllComptes();
        if (!comptes.isEmpty()) {
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "",comptes,1),HttpStatus.OK
            );
        } else {
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "Aucun compte trouvé",null,1),HttpStatus.OK
            );
        }
    }

    // Opération de suppression d'un compte
    @DeleteMapping("/comptes/{numCompte}")
    public ResponseEntity<JsonRespons> deleteCompte(@PathVariable("numCompte") String numCompte) {
        compteService.deleteCompte(numCompte);
        return new ResponseEntity<JsonRespons>(
                new JsonRespons(HttpStatus.OK.value(), "Compte supprimé avec succès",null,1),HttpStatus.OK
        );
    }

    @PutMapping("/comptes/depot")
    public ResponseEntity<JsonRespons> faireDepot(@RequestBody OptCompte optCompte){
        boolean res;
        try {
             res = compteService.faireDepot(optCompte.numCompte(), optCompte.montant());
            if(res)
            {

                return new ResponseEntity<JsonRespons>(
                        new JsonRespons(HttpStatus.OK.value(), "Dépot effectué avec succès",compteService.getCompteById(optCompte.numCompte()),1),HttpStatus.OK
                );
            }
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.NOT_FOUND.value(), "Compte introuvable",null,1),HttpStatus.NOT_FOUND
            );
        }catch (Exception e){
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "Donnée envoyée invalide",compteService.getCompteById(optCompte.numCompte()),1),HttpStatus.OK
            );
        }


    }

    @PostMapping("/comptes/retrait")
    public ResponseEntity<JsonRespons> faireRetrait(@RequestBody OptCompte optCompte){
        //return  new ResponseEntity<Boolean>(compteService.faireRetrait(optCompte.numCompte(), optCompte.montant()),HttpStatus.OK);
        boolean res;
        try {
            res = compteService.faireRetrait(optCompte.numCompte(), optCompte.montant());
            if(res)
            {

                return new ResponseEntity<JsonRespons>(
                        new JsonRespons(HttpStatus.OK.value(), "Retrait effectué avec succès",compteService.getCompteById(optCompte.numCompte()),1),HttpStatus.OK
                );
            }
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.NOT_FOUND.value(), "Montant invalide",null,1),HttpStatus.NOT_FOUND
            );
        }catch (Exception e){
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "Donnée envoyée invalide",null,1),HttpStatus.OK
            );
        }
    }

    @PostMapping("/comptes/virement")
    public ResponseEntity<JsonRespons> faireVirement(@RequestBody OptVirement optVirement){
       // return  new ResponseEntity<Boolean>(compteService.faireVirement(optVirement.emmeteur(),optVirement.recepteur(),optVirement.montant()),HttpStatus.OK);
        boolean res;
        try {
            res = compteService.faireVirement(optVirement.emmeteur(),optVirement.recepteur(),optVirement.montant());
            if(res)
            {

                return new ResponseEntity<JsonRespons>(
                        new JsonRespons(HttpStatus.OK.value(), "Virement effectué avec succès",null,1),HttpStatus.OK
                );
            }
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.NOT_FOUND.value(), "Echec du virement, solde insuffisant ou un compte introuvable",null,1),HttpStatus.NOT_FOUND
            );
        }catch (Exception e){
            return new ResponseEntity<JsonRespons>(
                    new JsonRespons(HttpStatus.OK.value(), "Donnée envoyée invalide",null,1),HttpStatus.OK
            );
        }
    }
}