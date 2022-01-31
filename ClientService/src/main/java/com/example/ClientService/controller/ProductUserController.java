package com.example.ClientService.controller;

import com.example.ClientService.payload.ProductUserDto;
import com.example.ClientService.service.ProductUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "This is to save a ProductUser in Db for a unique Client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Saved productUser form Db for a unique client.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Category not found with id : clientId",
                    content = @Content)
    })
    @PostMapping("/{clientId}/productUsers")
    public ResponseEntity<ProductUserDto> createProductUser(@PathVariable(name = "clientId") long clientId,
                                                            @RequestBody ProductUserDto productUserDto){

        LocalDate localDate = LocalDate.now();
        productUserDto.setCreatedDate(localDate);

        ProductUserDto createdProductUser = productUserService.createProductUser(clientId,productUserDto);

        return new ResponseEntity<>(createdProductUser, HttpStatus.CREATED);

    }


    @Operation(summary = "This is to fetch All the ProductUsers stored in Db for a unique Client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched All the ProductUsers form Db for a unique Client.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Not Available",
                    content = @Content)
    })
    @GetMapping("/{clientId}/productUsers")
    public List<ProductUserDto> getProductUsersByClientId(
            @PathVariable(name = "clientId") long clientId){

        return productUserService.getProductUsersByClientId(clientId);
    }



    @Operation(summary = "This is to fetch a unique productUser stored in Db for a unique Client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched productUser form Db for a unique Client.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "ProductUser not found with id : ClientId",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "ProductUser not from this Client",
                    content = @Content)
    })
    @GetMapping("/{clientId}/productUsers/{id}")
    public ResponseEntity<ProductUserDto> getProductUserById(@PathVariable(name = "clientId") long clientId,
                                                             @PathVariable(name = "id") long productUserId){

        ProductUserDto productUserDto = productUserService.getProductUserById(clientId,productUserId);

        return new ResponseEntity<>(productUserDto,HttpStatus.OK);
    }




    @Operation(summary = "This is to Update a unique productUser stored in Db for a unique Client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Updated productUser form Db for a unique Client.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "ProductUser not found with id : clientId",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Productuser not from this Client",
                    content = @Content)
    })
    @PutMapping("/{clientId}/productUsers/{id}")
    public ResponseEntity<ProductUserDto> updateProductUser(@PathVariable(name = "clientId") long clientId,
                                                            @PathVariable(name = "id") long productUserId,
                                                            @RequestBody ProductUserDto productUserDto){

        ProductUserDto updatedProductUser = productUserService.updateProductUser(clientId,productUserId,productUserDto);

        return new ResponseEntity<>(updatedProductUser,HttpStatus.OK);

    }



    @Operation(summary = "This is to delete a unique productUser stored in Db for a unique Client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "ProductUser deleted successfully.",
                    content = {@Content(mediaType = "text/plain;charset=UTF-8")}),
            @ApiResponse(responseCode = "404",
                    description = "ProductUser not found with id : productId",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "ProductUser not from this Client",
                    content = @Content)
    })
    @DeleteMapping("/{clientId}/productUsers/{id}")
    public ResponseEntity<String> deleteProductUser(@PathVariable(name = "clientId") long clientId,
                                                    @PathVariable(name = "id") long productUserId){

        productUserService.deleteProductUser(clientId, productUserId);

        return new ResponseEntity<>("ProductUser has been deleted!",HttpStatus.OK);

    }


}
