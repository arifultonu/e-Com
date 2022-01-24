package com.app.orderservice.serviceImpl;

import com.app.orderservice.entity.Shipment;
import com.app.orderservice.exception.ResourceNotFoundException;
import com.app.orderservice.repository.ShipmentRepository;
import com.app.orderservice.dto.ShipmentDto;
import com.app.orderservice.service.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {
    @Autowired
    ShipmentRepository shipmentRepository;

    @Override
    public List<ShipmentDto> getAllShipment() {

        List<Shipment> shipments = shipmentRepository.findAll();

        log.info("Inside getAllShipment of ShipmentService");

        return shipments.stream().map(shipment ->
                mapToShipmentDto(shipment)).collect(Collectors.toList());
    }

    @Override
    public ShipmentDto save(ShipmentDto shipmentDto) {
        //business logic
        Shipment shipment = mapToEntity(shipmentDto);

        //save Shipment to DB
        Shipment newShipment = shipmentRepository.save(shipment);

        //Convert Entity to Dto
        ShipmentDto newShipmentDto = mapToShipmentDto(newShipment);

        log.info("Inside createShipment of ShipmentService");

        return newShipmentDto;
    }


    @Override
    public ShipmentDto findShipmentById(long shipmentId) {

        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow(()-> new ResourceNotFoundException("Shipment","id",shipmentId));

        log.info("Inside findByShipmentId of ShipmentService");
        return mapToShipmentDto(shipment);
    }

    @Override
    public ShipmentDto updateShipmentById(long shipmentId, ShipmentDto shipmentDto) {

        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow(()-> new ResourceNotFoundException("Shipment","id",shipmentId));

        shipment.setShipmentOrderId(shipmentDto.getShipmentOrderId());
        shipment.setShipmentDate(shipmentDto.getShipmentDate());

        Shipment updatedShipment = shipmentRepository.save(shipment);

        log.info("Inside updateByShipmentId of ShipmentService");

        return mapToShipmentDto(updatedShipment);
    }

    @Override
    public void deleteShipmentById(long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow(()-> new ResourceNotFoundException("Shipment","id",shipmentId));

        log.info("Inside deleteByShipmentId of ShipmentService");

        shipmentRepository.delete(shipment);
    }

    public Shipment mapToEntity(ShipmentDto shipmentDto){
        Shipment shipment= new Shipment();
        /*shipment.setShipmentId(shipmentDto.getShipmentId());*/
        shipment.setShipmentOrderId(shipmentDto.getShipmentOrderId());
        shipment.setShipmentDate(shipmentDto.getShipmentDate());
        return shipment;
    }

    private ShipmentDto mapToShipmentDto(Shipment shipment) {
        ShipmentDto shipmentDto = new ShipmentDto();
        shipmentDto.setShipmentId(shipment.getShipmentId());
        shipmentDto.setShipmentOrderId(shipment.getShipmentOrderId());
        shipmentDto.setShipmentDate(shipment.getShipmentDate());
        return shipmentDto;
    }
}
