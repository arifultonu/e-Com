package com.app.orderservice.controller;

import com.app.orderservice.responseModel.ShipmentDto;
import com.app.orderservice.service.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/shipment_api/")
@Slf4j
public class ShipmentController {
   /* @Autowired
    ShipmentService shipmentService;

    @PostMapping("/category/{categoryId}/product")
    public ShipmentDto createShipment(@PathVariable("categoryId") Long categoryId,
                                      @RequestBody ShipmentDto shipmentDto) {

        log.info("Inside the createProduct Controller");

        return shipmentService.createShipment(categoryId, ShipmentDto);
    }

    @GetMapping("/product")
    public List<ProductDto> getAllProduct() {

        log.info("Inside the getAllProduct Controller");

        return productService.getAllProduct();
    }

    @GetMapping("/category/{categoryId}/product/{productId}")
    public ProductDto getProductById(@PathVariable("categoryId") Long categoryId,
                                     @PathVariable("productId") Long productId) {

        log.info("Inside the getProductById Controller");

        return productService.getProductById(categoryId,productId);
    }

    @PutMapping("/category/{categoryId}/product/{productId}")
    public ProductDto updateProduct(@PathVariable("categoryId") Long categoryId,
                                    @PathVariable("productId") Long productId,
                                    @RequestBody ProductDto productDto) {

        log.info("Inside the updateProduct Controller");

        return productService.updateProduct(categoryId, productId, productDto);

    }



    @DeleteMapping("/category/{categoryId}/product/{productId}")
    public String deleteProduct(@PathVariable("categoryId") Long categoryId,
                                @PathVariable("productId") Long productId) {

        log.info("Inside the deleteProduct Controller");

        productService.deleteProduct(categoryId, productId);
        return "Product Deleted Successfully";

    }*/
}
