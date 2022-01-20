package com.example.ClientService.repository;

import com.example.ClientService.entity.ProductUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductUserRepository extends JpaRepository<ProductUser, Long> {

    List<ProductUser> findByClientId(long clientId);

}
