package com.example.egaAPI.Repository;

import com.example.egaAPI.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
