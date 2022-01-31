package com.example.ClientService.service.impl;

import com.example.ClientService.entity.Client;
import com.example.ClientService.entity.ProductUser;
import com.example.ClientService.exception.ClientServiceApiException;
import com.example.ClientService.exception.ResourceNotFoundException;
import com.example.ClientService.payload.ProductUserDto;
import com.example.ClientService.repository.ClientRepository;
import com.example.ClientService.repository.ProductUserRepository;
import com.example.ClientService.service.ProductUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductUserServiceImpl implements ProductUserService {

    private ProductUserRepository productUserRepository;
    private ClientRepository clientRepository;

    @Autowired
    public ProductUserServiceImpl(ProductUserRepository productUserRepository, ClientRepository clientRepository) {
        this.productUserRepository = productUserRepository;
        this.clientRepository = clientRepository;
    }

    //create
    @Override
    public ProductUserDto createProductUser(long clientId, ProductUserDto productUserDto) {

        ProductUser productUser = mapTOEntity(productUserDto);

        //retrieve client entity by clientId
        Client client = clientRepository.findById(clientId).orElseThrow(()->
                new ResourceNotFoundException("Client", "id", clientId));

        //set client to ProductUser entity
        productUser.setClient(client);

        //productuser entity to DB
        ProductUser newPrductUser = productUserRepository.save(productUser);

        return mapToDto(newPrductUser);
    }

    //get list
    @Override
    public List<ProductUserDto> getProductUsersByPostId(long clientId) {

        List<ProductUser> productUsers = productUserRepository.findByClientId(clientId);

        return productUsers.stream().map(productUser -> mapToDto(productUser)).collect(Collectors.toList());
    }

    //get 1 entity
    @Override
    public ProductUserDto getProductUserById(long clientId, long productUserId) {

        ProductUser productUser = verifyProductUserOfClient(clientId, productUserId);

        return mapToDto(productUser);
    }

    //update
    @Override
    public ProductUserDto updateProductUser(long clientId, long productUserId, ProductUserDto productUserRequest) {

        ProductUser productUser = verifyProductUserOfClient(clientId, productUserId);

        productUser.setPrductUserName(productUserRequest.getProductUserName());
        //productUser.setCreatedDate(productUserRequest.getCreatedDate());

        ProductUser updateProductUser = productUserRepository.save(productUser);

        return mapToDto(updateProductUser);
    }

    //delete
    @Override
    public void deleteProductUser(long clientId, long productUserId) {

        ProductUser productUser = verifyProductUserOfClient(clientId, productUserId);

        productUserRepository.delete(productUser);
    }


    //verify if product user is belong to the Client
    private ProductUser verifyProductUserOfClient(long clientId, long productUserId){

        //retrieve client entity by clientId
        Client client = clientRepository.findById(clientId).orElseThrow(()->
                new ResourceNotFoundException("Client", "id", clientId));

        //retrieve productUser entity by clientId
        ProductUser productUser = productUserRepository.findById(productUserId).orElseThrow(()->
                new ResourceNotFoundException("ProductUser", "id", productUserId));

        if(productUser.getClient().getId() != client.getId()){

            throw new ClientServiceApiException(HttpStatus.BAD_REQUEST, "productUser does not belong to the client");
        }

        return productUser;

    }

    //map Dto to Entity
    private ProductUser mapTOEntity(ProductUserDto productUserDto){

        ProductUser productUser = new ProductUser();

        productUser.setId(productUserDto.getId());
        productUser.setPrductUserName(productUserDto.getProductUserName());
        productUser.setCreatedDate(productUserDto.getCreatedDate());

        return productUser;
    }

    //map Entity to Dto
    private ProductUserDto mapToDto(ProductUser productUser){

        ProductUserDto productUserDto = new ProductUserDto();

        productUserDto.setId(productUser.getId());
        productUserDto.setProductUserName(productUser.getPrductUserName());
        productUserDto.setCreatedDate(productUser.getCreatedDate());

        return productUserDto;
    }

}
