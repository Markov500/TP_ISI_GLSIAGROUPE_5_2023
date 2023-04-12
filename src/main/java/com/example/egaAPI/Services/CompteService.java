package com.example.egaAPI.Services;


import com.example.egaAPI.Repository.ClientRepository;
import com.example.egaAPI.Repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteService {
    @Autowired
    CompteRepository compteRepository;
}
