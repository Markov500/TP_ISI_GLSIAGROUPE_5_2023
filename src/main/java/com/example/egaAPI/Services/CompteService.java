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
        //Client client = compte.getClient();
       /* Client client = clientRepository.findById(compte.getClient().getId()).get();
        if (client.getId() == null) {
            clientRepository.save(client);
        }
        Compte realCompte = new Compte(compte.getTypeCompte(), client);*/

        return compteRepository.save(compte);
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

    public boolean faireDepot(String numCompte, Double montant){
        var compte= getCompteById(numCompte);
        if (compte.isPresent()){
            var cpt= compte.get();
            cpt.depot(montant);
            compteRepository.save(cpt);
            return true;
        }
        return false;
    }

    public boolean faireRetrait(String numCompte, Double montant){
        var compte= getCompteById(numCompte);
        if (compte.isPresent()){
            var cpt= compte.get();
            var rep=cpt.retrait(montant);
            if (rep){
                compteRepository.save(cpt);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean faireVirement (String numCompte, String recepteur, Double montant){
        var emmeteur= getCompteById(numCompte) ;
        var destinataire= getCompteById(recepteur);
        if (emmeteur.isPresent() && destinataire.isPresent()){
            var cpt_emmeteur= emmeteur.get();
            var cpt_destinataire=destinataire.get();
            var rep=cpt_emmeteur.virement(montant, cpt_destinataire);
            compteRepository.save(cpt_emmeteur);
            compteRepository.save(cpt_destinataire);
            return rep;
        }
        return false;
    }

}