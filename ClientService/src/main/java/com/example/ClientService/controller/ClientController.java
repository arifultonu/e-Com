package com.example.ClientService.controller;

import com.example.ClientService.payload.ClientDto;
import com.example.ClientService.payload.ClientResponse;
import com.example.ClientService.service.ClientService;
import com.example.ClientService.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ClientResponse getAllClients(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){

        return clientService.getAllClients(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable(name = "id") long id){

        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto clientDto,
                                                  @PathVariable(name = "id") long id){

        ClientDto updatedClient = clientService.updateClient(clientDto,id);

        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable(name = "id") long id){

        clientService.deleteClientById(id);

        return new ResponseEntity<>("Client Deleted Successfully", HttpStatus.OK);
    }

}
