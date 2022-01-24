package com.app.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_product_id", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Shipment> shipment = new HashSet<>();
    */

}
