package com.example.ClientService.repository;

import com.example.ClientService.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long>{


}
