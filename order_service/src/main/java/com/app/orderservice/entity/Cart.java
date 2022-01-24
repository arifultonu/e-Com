package com.app.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Cart")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cart {
    @Id
    @SequenceGenerator(
            name = "cartId",
            sequenceName = "cartId",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cartId"
    )
    private long cartId;

    @Column(nullable = false)
    private Date cartAddDate;

    @Column(nullable = false)
    private String cartSessionId;

    @Column(nullable = false)
    private long cartUserId;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_user_id", nullable = false)
    private User user;
     */
}
