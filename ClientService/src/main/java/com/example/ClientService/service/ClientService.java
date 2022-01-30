package com.example.ClientService.service;

import com.example.ClientService.payload.ClientDto;
import com.example.ClientService.payload.ClientResponse;

public interface ClientService {

    ClientDto createClient(ClientDto clientDto);

    ClientResponse getAllClients(int pageNo, int pageSize, String sortBy, String sortDir);

    ClientDto getClientById(long id);

    ClientDto updateClient(ClientDto clientDto, long id);

    void deleteClientById(long id);
}