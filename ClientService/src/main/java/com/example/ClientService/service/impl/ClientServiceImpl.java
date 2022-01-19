package com.example.ClientService.service.impl;

import com.example.ClientService.entity.Client;
import com.example.ClientService.payload.ClientDto;
import com.example.ClientService.payload.ClientResponse;
import com.example.ClientService.repository.ClientRepository;
import com.example.ClientService.service.ClientService;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public ClientDto createClient(ClientDto clientDto) {

        //convert Dto to entity
        Client client = mapToEntity(clientDto);

        Client newClient = clientRepository.save(client);

        //convert Dto to entity
        ClientDto clientCreatedResponse = mapTODto(newClient);

        return clientCreatedResponse;
    }

    @Override
    public ClientResponse getAllClients(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending() ;

        //create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Client> clients = clientRepository.findAll(pageable);

        List<Client> listOfClients = clients.getContent();

    }

    //Convert Dto to Entity
    public Client mapToEntity(ClientDto clientDto){

        Client client = new Client();

        client.setClientName(clientDto.getClientName());
        client.setClientAddress(clientDto.getClientAddress());
        client.setCreatedDate(clientDto.getCreatedDate());
        client.setClientLogo(clientDto.getClientLogo());
        client.setClientType(clientDto.getClientType());

        return client;
    }

    public  ClientDto mapTODto(Client client){

        ClientDto clientDto = new ClientDto();

        clientDto.setId(client.getId());
        clientDto.setClientName(client.getClientName());
        clientDto.setClientAddress(client.getClientAddress());
        clientDto.setCreatedDate(client.getCreatedDate());
        clientDto.setClientLogo(client.getClientLogo());
        clientDto.setClientType(client.getClientType());

        return clientDto;
    }
}
