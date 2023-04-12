package com.example.egaAPI.Controller;

import com.example.egaAPI.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    @Autowired
    ClientService clientService;
}
