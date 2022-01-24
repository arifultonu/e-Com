package com.app.orderservice.service;

import com.app.orderservice.dto.ShipmentDto;

import java.util.List;

public interface ShipmentService {
    List<ShipmentDto> getAllShipment();

    ShipmentDto save(ShipmentDto shipmentDto);

    ShipmentDto findShipmentById(long shipmentId);

    ShipmentDto updateShipmentById(long shipmentId, ShipmentDto shipmentDto);

    void deleteShipmentById(long shipmentId);
}
