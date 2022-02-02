package com.app.orderservice.controller;

import com.app.orderservice.dto.ShipmentDto;
import com.app.orderservice.service.ShipmentService;
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
@RequestMapping("/shipment-api/")
@Slf4j
public class ShipmentController {

   @Autowired
    ShipmentService shipmentService;

    @Operation(summary = "This is to fetch all Shipments from Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched all Shipments  from Db",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Shipments not found!",
                    content = @Content),
    })
    @GetMapping(value="/shipments")
    public List<ShipmentDto> getAllShipment() {

        log.info("Inside the getAllProduct Controller");

        return shipmentService.getAllShipment();
    }

    @Operation(summary = "This is to save a Shipment in the Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Saved Shipment from Db.",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping(value="/save")
    public ShipmentDto save(@RequestBody ShipmentDto shipmentDto ){
        return shipmentService.save(shipmentDto);
    }

    @Operation(summary = "This is to fetch unique Shipment stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched Shipment form Db.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Shipment not found with id : shipmentId",
                    content = @Content),
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<ShipmentDto> findShipmentById(@PathVariable(name = "id") long shipmentId) {
        log.info("Inside the findShipmentById Controller");
        ShipmentDto shipmentDtoResponse = shipmentService.findShipmentById(shipmentId);
        return new ResponseEntity<>(shipmentDtoResponse, HttpStatus.OK);
    }

    @Operation(summary = "This is to update a unique Shipment stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Updated Shipment form Db.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Shipment not found with id : ShipmentId",
                    content = @Content),
    })
    @PutMapping(value="/update/{id}")
    public ResponseEntity<ShipmentDto> updateShipmentById(@PathVariable(name = "id") long shipmentId, @RequestBody ShipmentDto shipmentDto) {
        log.info("Inside the updateShipmentById Controller");
        ShipmentDto shipmentDtoResponse =  shipmentService.updateShipmentById(shipmentId, shipmentDto);

        return new ResponseEntity<>(shipmentDtoResponse, HttpStatus.OK);
    }

    @Operation(summary = "This is to delete a unique Shipment stored in Db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Shipment Deleted Successfully form Db.",
                    content = {@Content(mediaType = "text/plain;charset=UTF-8")}),
            @ApiResponse(responseCode = "404",
                    description = "Shipment not found with id : shipmentId",
                    content = @Content),
    })
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<String> deleteShipmentById(@PathVariable(name = "id") long shipmentId) {

        log.info("Inside the deleteShipmentById Controller");

        shipmentService.deleteShipmentById(shipmentId);

        return new ResponseEntity<>("Shipment Deleted Successfully", HttpStatus.OK);
    }
}
