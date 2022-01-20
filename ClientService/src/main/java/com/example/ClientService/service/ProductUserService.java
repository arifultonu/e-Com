package com.example.ClientService.service;

import com.example.ClientService.payload.ProductUserDto;

import java.util.List;

public interface ProductUserService {

    ProductUserDto createProductUser(long clientId, ProductUserDto productUserDto);

    List<ProductUserDto> getProductUsersByPostId(long clientId);

    ProductUserDto getProductUserById(long clientId,long productUserId);

    ProductUserDto updateProductUser(long clientId, long productUserId, ProductUserDto productUserRequest);

    void  deleteProductUser(long clientId, long productUserId);

}
