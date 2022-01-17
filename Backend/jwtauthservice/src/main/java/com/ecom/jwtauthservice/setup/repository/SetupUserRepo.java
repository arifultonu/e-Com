package com.ecom.jwtauthservice.setup.repository;

import com.ecom.jwtauthservice.setup.entity.SetupUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SetupUserRepo extends JpaRepository<SetupUserEntity, Long> {

    @Query(" from  SetupUserEntity  where  username=:username")
    SetupUserEntity findByUserName(String username);
}
