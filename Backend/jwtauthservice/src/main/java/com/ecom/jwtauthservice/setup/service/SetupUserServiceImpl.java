package com.ecom.jwtauthservice.setup.service;


import com.ecom.jwtauthservice.common.model.MessageResponse;
import com.ecom.jwtauthservice.setup.entity.SetupUserEntity;
import com.ecom.jwtauthservice.setup.repository.SetupUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetupUserServiceImpl implements SetupUserService{

    @Autowired
    SetupUserRepo setupUserRepo;

    @Override
    public MessageResponse addUser(SetupUserEntity setupUserEntity) {
        MessageResponse messageResponse = new MessageResponse();
        if(setupUserEntity.getPassword().equals("admin@123")){
            setupUserEntity.setRole("Admin");
        } else{
            setupUserEntity.setRole("User");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(setupUserEntity.getPassword());
        setupUserEntity.setPassword(password);

        setupUserRepo.save(setupUserEntity);
        messageResponse.setResponseCode("1");
        messageResponse.setResponseMessage("Add New User Successfully!");
        return messageResponse;
    }

    @Override
    public MessageResponse updateUser(SetupUserEntity setupUserEntity) {
        MessageResponse messageResponse = new MessageResponse();
        SetupUserEntity existingUser = setupUserRepo.findById(setupUserEntity.getId()).orElse(null);
        existingUser.setName(setupUserEntity.getName());
        setupUserRepo.save(existingUser);
        messageResponse.setResponseCode("1");
        messageResponse.setResponseMessage("Update User Successfully!");
        return messageResponse;
    }

    @Override
    public List<SetupUserEntity> updateAllUser(List<SetupUserEntity> setupUserEntity) {
        return setupUserRepo.saveAll(setupUserEntity);
    }

    @Override
    public List<SetupUserEntity> getAllUserList() {
        return setupUserRepo.findAll();
    }

    @Override
    public MessageResponse deleteUser(Long id) {
        MessageResponse messageResponse = new MessageResponse();
        setupUserRepo.deleteById(id);
        messageResponse.setResponseCode("1");
        messageResponse.setResponseMessage("Delete User Successfully!");
        return messageResponse;
    }
}
