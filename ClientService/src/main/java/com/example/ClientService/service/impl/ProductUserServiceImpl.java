package com.example.ClientService.service.impl;

import com.example.ClientService.entity.Client;
import com.example.ClientService.entity.ProductUser;
import com.example.ClientService.exception.ResourceNotFoundException;
import com.example.ClientService.payload.ProductUserDto;
import com.example.ClientService.repository.ClientRepository;
import com.example.ClientService.repository.ProductUserRepository;
import com.example.ClientService.service.ProductUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUserServiceImpl implements ProductUserService {

    private ProductUserRepository productUserRepository;
    private ClientRepository clientRepository;

    @Autowired
    public ProductUserServiceImpl(ProductUserRepository productUserRepository, ClientRepository clientRepository) {
        this.productUserRepository = productUserRepository;
        this.clientRepository = clientRepository;
    }

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

    @Override
    public List<ProductUserDto> getProductUsersByPostId(long clientId) {
        return null;
    }

    @Override
    public ProductUserDto getProductUserById(long clientId, long productUserId) {
        return null;
    }

    @Override
    public ProductUserDto updateProductUser(long clientId, long productUserId, ProductUserDto productUserRequest) {
        return null;
    }

    @Override
    public void deleteProductUser(long clientId, long productUserId) {

    }

    private ProductUser mapTOEntity(ProductUserDto productUserDto){

        ProductUser productUser = new ProductUser();

        productUser.setId(productUserDto.getId());
        productUser.setPrductUserName(productUserDto.getProductUserName());
        productUser.setCreatedDate(productUserDto.getCreatedDate());

        return productUser;
    }

    private ProductUserDto mapToDto(ProductUser productUser){

        ProductUserDto productUserDto = new ProductUserDto();

        productUserDto.setId(productUser.getId());
        productUserDto.setProductUserName(productUser.getPrductUserName());
        productUserDto.setCreatedDate(productUser.getCreatedDate());

        return productUserDto;
    }

}
