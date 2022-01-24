package com.app.orderservice.controller;

import com.app.orderservice.dto.ShipmentDto;
import com.app.orderservice.service.ShipmentService;
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

    @GetMapping(value="/shipments")
    public List<ShipmentDto> getAllShipment() {

        log.info("Inside the getAllProduct Controller");

        return shipmentService.getAllShipment();
    }

    @PostMapping(value="/save")
    public ShipmentDto save(@RequestBody ShipmentDto shipmentDto ){
        return shipmentService.save(shipmentDto);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ShipmentDto> findShipmentById(@PathVariable(name = "id") long shipmentId) {
        log.info("Inside the findShipmentById Controller");
        ShipmentDto shipmentDtoResponse = shipmentService.findShipmentById(shipmentId);
        return new ResponseEntity<>(shipmentDtoResponse, HttpStatus.OK);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<ShipmentDto> updateShipmentById(@PathVariable(name = "id") long shipmentId, @RequestBody ShipmentDto shipmentDto) {
        log.info("Inside the updateShipmentById Controller");
        ShipmentDto shipmentDtoResponse =  shipmentService.updateShipmentById(shipmentId, shipmentDto);

        return new ResponseEntity<>(shipmentDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<String> deleteShipmentById(@PathVariable(name = "id") long shipmentId) {

        log.info("Inside the deleteShipmentById Controller");

        shipmentService.deleteShipmentById(shipmentId);

        return new ResponseEntity<>("Shipment Deleted Successfully", HttpStatus.OK);
    }
}
