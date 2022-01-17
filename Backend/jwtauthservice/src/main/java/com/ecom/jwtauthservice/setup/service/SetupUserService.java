package com.ecom.jwtauthservice.setup.service;


import com.ecom.jwtauthservice.common.model.MessageResponse;
import com.ecom.jwtauthservice.setup.entity.SetupUserEntity;

import java.util.List;

public interface SetupUserService {
    MessageResponse addUser(SetupUserEntity setupUserEntity);
    MessageResponse updateUser(SetupUserEntity setupUserEntity);
    List<SetupUserEntity> updateAllUser(List<SetupUserEntity> setupUserEntity);
    List<SetupUserEntity> getAllUserList();
    MessageResponse deleteUser(Long id);
}
