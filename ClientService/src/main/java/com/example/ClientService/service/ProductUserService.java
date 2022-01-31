package com.example.ClientService.service;

import com.example.ClientService.payload.ClientResponse;
import com.example.ClientService.payload.ProductUserDto;
import com.example.ClientService.payload.ProductUserResponse;

import java.util.List;

public interface ProductUserService {

    ProductUserDto createProductUser(long clientId, ProductUserDto productUserDto);

    List<ProductUserDto> getProductUsersByClientId(long clientId);
    //ProductUserResponse getAllProductUsers(long clientId,int pageNo, int pageSize, String sortBy, String sortDir);

    ProductUserDto getProductUserById(long clientId,long productUserId);

    ProductUserDto updateProductUser(long clientId, long productUserId, ProductUserDto productUserRequest);

    void  deleteProductUser(long clientId, long productUserId);

}
