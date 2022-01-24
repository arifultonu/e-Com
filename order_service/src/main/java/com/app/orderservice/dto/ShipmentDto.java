package com.app.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDto {
    public Long shipmentId;
    public Long shipmentOrderId;
    public Date shipmentDate;
}
