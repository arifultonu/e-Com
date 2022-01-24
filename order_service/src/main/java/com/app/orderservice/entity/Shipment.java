package com.app.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Shipment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    @Id
    @SequenceGenerator(
            name = "shipmentId",
            sequenceName = "shipmentId",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shipmentId"
    )
    private long shipmentId;

    @Column(nullable = false)
    private long shipmentOrderId;

    @Column(nullable = false)
    private Date shipmentDate;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_order_id", nullable = false)
    private Order order;
    */
}
