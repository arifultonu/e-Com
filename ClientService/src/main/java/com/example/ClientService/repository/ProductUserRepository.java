package com.example.ClientService.repository;

import com.example.ClientService.entity.ProductUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductUserRepository extends JpaRepository<ProductUser, Long> {

}
