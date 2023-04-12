package com.example.egaAPI.Repository;

import com.example.egaAPI.Entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, String> {
}
