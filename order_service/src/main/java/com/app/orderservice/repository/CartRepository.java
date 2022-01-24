package com.app.orderservice.repository;

import com.app.orderservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart, Long> {

}
