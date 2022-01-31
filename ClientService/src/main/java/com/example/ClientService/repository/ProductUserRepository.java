package com.example.ClientService.repository;

import com.example.ClientService.entity.ProductUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductUserRepository extends JpaRepository<ProductUser, Long> {

    List<ProductUser> findByClientId(long clientId);

    //List<ProductUser> findByClientId(long clientId, Pageable pageable);

    //Long removeByClientId(long clientId);

}
