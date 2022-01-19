package com.example.ClientService.controller;

import com.example.ClientService.payload.ClientDto;
import com.example.ClientService.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/client-api/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto){

        LocalDate localDate = LocalDate.now();
        clientDto.setCreatedDate(localDate);

        return new ResponseEntity<>(clientService.createClient(clientDto), HttpStatus.CREATED);

    }
}
