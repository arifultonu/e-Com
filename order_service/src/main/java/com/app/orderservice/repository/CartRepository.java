package com.app.orderservice.repository;

import com.app.orderservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("From Cart")
    public List<Cart> getAllCarts();
}
