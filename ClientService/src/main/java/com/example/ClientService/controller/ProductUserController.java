package com.example.ClientService.controller;

import com.example.ClientService.payload.ProductUserDto;
import com.example.ClientService.service.ProductUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/client-api/clients")
public class ProductUserController {

    private ProductUserService productUserService;

    @Autowired
    public ProductUserController(ProductUserService productUserService) {
        this.productUserService = productUserService;
    }

    @PostMapping("/{clientId}/productUsers")
    public ResponseEntity<ProductUserDto> createProductUser(@PathVariable(name = "clientId") long clientId,
                                                            @RequestBody ProductUserDto productUserDto){

        LocalDate localDate = LocalDate.now();
        productUserDto.setCreatedDate(localDate);

        ProductUserDto createdProductUser = productUserService.createProductUser(clientId,productUserDto);

        return new ResponseEntity<>(createdProductUser, HttpStatus.CREATED);

    }



}
