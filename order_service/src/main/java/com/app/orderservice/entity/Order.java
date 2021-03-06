package com.app.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @SequenceGenerator(
            name = "orderId",
            sequenceName = "orderId",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orderId"
    )
    private long orderId;

    @Column(nullable = false)
    private String orderUserId;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private String orderProductId;

    @Column(nullable = false)
    private String orderStatus;

    @Column(nullable = false)
    private Date orderApprovalDate;

    @Column(nullable = false)
    private Date orderUpdateDate;

    @Column(nullable = false)
    private String orderApprovedStatus;

}
