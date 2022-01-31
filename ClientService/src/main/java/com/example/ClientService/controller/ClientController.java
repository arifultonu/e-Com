package com.example.ClientService.controller;

import com.example.ClientService.payload.ClientDto;
import com.example.ClientService.payload.ClientResponse;
import com.example.ClientService.service.ClientService;
import com.example.ClientService.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "This is to save a Client in the Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Saved Category from Db.",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto){

        LocalDate localDate = LocalDate.now();
        clientDto.setCreatedDate(localDate);

        return new ResponseEntity<>(clientService.createClient(clientDto), HttpStatus.CREATED);

    }


    @Operation(summary = "This is to fetch All the Clients stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched a page of Clients form Db.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "NOT Available",
                    content = @Content)
    })
    @GetMapping
    public ClientResponse getAllClients(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){

        return clientService.getAllClients(pageNo, pageSize, sortBy, sortDir);
    }


    @Operation(summary = "This is to fetch a unique Client stored in Db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched a unique Client stored in Db.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Client not found with id : clientId",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable(name = "id") long id){

        return ResponseEntity.ok(clientService.getClientById(id));
    }



    @Operation(summary = "This is to Update a unique Client stored in Db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Updated Client form Db.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Client not found with id : clientId",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto clientDto,
                                                  @PathVariable(name = "id") long id){

        ClientDto updatedClient = clientService.updateClient(clientDto,id);

        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }



    @Operation(summary = "This is to delete a unique Client stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Client deleted successfully.",
                    content = {@Content(mediaType = "text/plain;charset=UTF-8")}),
            @ApiResponse(responseCode = "404",
                    description = "Client not found with id : clientId",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable(name = "id") long id){

        clientService.deleteClientById(id);

        return new ResponseEntity<>("Client Deleted Successfully", HttpStatus.OK);
    }

}
