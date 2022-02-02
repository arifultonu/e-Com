package com.app.orderservice.controller;


import com.app.orderservice.dto.CartDto;
import com.app.orderservice.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/cart-api/")
public class CartController {
    @Autowired
    CartService cartService;

    @Operation(summary = "This is to fetch all Carts from Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched all Carts from Db",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Cart not found!",
                    content = @Content),
    })
    @GetMapping(value="/carts")
    public List<CartDto> getAllCart() {

        log.info("Inside the getAllCart Controller");

        return cartService.getAllCart();
    }

    @Operation(summary = "This is to save a Cart in the Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Saved Cart from Db.",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping(value="/save")
    public CartDto save(@RequestBody CartDto cartDto){
        return cartService.save(cartDto);
    }


    @Operation(summary = "This is to fetch Cart by Id stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched Cart cartId form Db.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Cart not found with id : cartId",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content),
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<CartDto> findCartById(@PathVariable(name = "id") long cartId) {
        log.info("Inside the findCartById Controller");
        CartDto cartDtoResponse = cartService.findCartById(cartId);
        return new ResponseEntity<>(cartDtoResponse, HttpStatus.OK);
    }

    @Operation(summary = "This is to update a unique Cart stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Updated Cart form Db.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Cart not found with id : cartId",
                    content = @Content),
    })
    @PutMapping(value="/update/{id}")
    public ResponseEntity<CartDto> updateCartById(@PathVariable(name = "id") long cartId, @RequestBody CartDto cartDto) {
        log.info("Inside the updateCartById Controller");
        CartDto cartDtoResponse =  cartService.updateCartById(cartId, cartDto);

        return new ResponseEntity<>(cartDtoResponse, HttpStatus.OK);
    }

    @Operation(summary = "This is to delete a unique Cart stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cart Deleted Successfully form Db.",
                    content = {@Content(mediaType = "text/plain;charset=UTF-8")}),
            @ApiResponse(responseCode = "404",
                    description = "Cart not found with id : cartId",
                    content = @Content),
    })
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<String> deleteCartById(@PathVariable(name = "id") long cartId) {

        log.info("Inside the deleteCartById Controller");

        cartService.deleteCartById(cartId);

        return new ResponseEntity<>("Cart Deleted Successfully", HttpStatus.OK);
    }

}
