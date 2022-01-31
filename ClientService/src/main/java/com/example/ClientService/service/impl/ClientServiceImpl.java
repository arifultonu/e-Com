package com.example.ClientService.service.impl;

import com.example.ClientService.entity.Client;
import com.example.ClientService.entity.ProductUser;
import com.example.ClientService.exception.ResourceNotFoundException;
import com.example.ClientService.payload.ClientDto;
import com.example.ClientService.payload.ClientResponse;
import com.example.ClientService.repository.ClientRepository;
import com.example.ClientService.repository.ProductUserRepository;
import com.example.ClientService.service.ClientService;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    private ProductUserRepository productUserRepository;

    private ProductUserServiceImpl productUserService;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository,
                             ProductUserRepository productUserRepository,
                             ProductUserServiceImpl productUserService) {

        this.clientRepository = clientRepository;
        this.productUserRepository = productUserRepository;
        this.productUserService = productUserService;
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

        //get content for page object
        List<Client> listOfClients = clients.getContent();

        List<ClientDto> content = listOfClients.stream().map(client ->
                mapTODto(client)).collect(Collectors.toList());

        ClientResponse clientResponse = new ClientResponse();

        clientResponse.setContent(content);
        clientResponse.setPageNo(clients.getNumber());
        clientResponse.setPageSize(clients.getSize());
        clientResponse.setTotalElements(clients.getTotalElements());
        clientResponse.setTotalPages(clients.getTotalPages());
        clientResponse.setLast(clients.isLast());

        return clientResponse;

    }

    @Override
    public ClientDto getClientById(long id) {

        Client client = clientRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Client", "id", id));

        return mapTODto(client);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto, long id) {
        Client client = clientRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Client", "id", id));

        client.setClientName(clientDto.getClientName());
        client.setClientAddress(clientDto.getClientAddress());
        client.setClientLogo(clientDto.getClientLogo());
        client.setClientType(clientDto.getClientType());

        Client updateClient = clientRepository.save(client);

        return mapTODto(updateClient);

    }

    //@Transactional
    @Override
    public void deleteClientById(long id) {
        Client client = clientRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Client", "id", id));

        List<ProductUser> productUsers = productUserRepository.findByClientId(id);

        for (ProductUser productUser : productUsers) {
            System.out.println(productUser.getPrductUserName()+" ; "+ productUser.getId());
            productUserService.deleteProductUser(id,productUser.getId());
        }
        //productUserRepository.removeByClientId(id);
        
        clientRepository.delete(client);

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
