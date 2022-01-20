package com.example.ClientService.controller;

import com.example.ClientService.payload.ProductUserDto;
import com.example.ClientService.service.ProductUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/{clientId}/productUsers")
    public List<ProductUserDto> getProductUsersByClientId(@PathVariable(name = "clientId") long clientId){

        return productUserService.getProductUsersByPostId(clientId);
    }

    @GetMapping("/{clientId}/productUsers/{id}")
    public ResponseEntity<ProductUserDto> getProductUserById(@PathVariable(name = "clientId") long clientId,
                                                             @PathVariable(name = "id") long productUserId){

        ProductUserDto productUserDto = productUserService.getProductUserById(clientId,productUserId);

        return new ResponseEntity<>(productUserDto,HttpStatus.OK);
    }

    @PutMapping("/{clientId}/productUsers/{id}")
    public ResponseEntity<ProductUserDto> updateProductUser(@PathVariable(name = "clientId") long clientId,
                                                            @PathVariable(name = "id") long productUserId,
                                                            @RequestBody ProductUserDto productUserDto){

        ProductUserDto updatedProductUser = productUserService.updateProductUser(clientId,productUserId,productUserDto);

        return new ResponseEntity<>(updatedProductUser,HttpStatus.OK);

    }

    @DeleteMapping("/{clientId}/productUsers/{id}")
    public ResponseEntity<String> deleteProductUser(@PathVariable(name = "clientId") long clientId,
                                                    @PathVariable(name = "id") long productUserId){

        productUserService.deleteProductUser(clientId, productUserId);

        return new ResponseEntity<>("ProductUser has been deleted!",HttpStatus.OK);

    }


}
