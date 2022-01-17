package com.ecom.jwtauthservice.setup.controller;


import com.ecom.jwtauthservice.common.model.DBData;
import com.ecom.jwtauthservice.common.model.MessageResponse;
import com.ecom.jwtauthservice.setup.entity.SetupUserEntity;
import com.ecom.jwtauthservice.setup.service.SetupUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = DBData.CROSS_ORIGIN)
@Slf4j
public class SetupUserController {

    @Autowired
    private SetupUserService setupUserService;

    //Done - param- {"":""}
    //Save a User
    @PostMapping("/addUser")
    public MessageResponse addUser(@RequestBody SetupUserEntity setupUserEntity) {
        return setupUserService.addUser(setupUserEntity);
    }

    //Done- param-
    //get All User //Done
    @GetMapping("/getAllUser")
    public List<SetupUserEntity> findAllUser() {
        return setupUserService.getAllUserList();
    }

    //Done param-RequestBody- {"":""}
    //Update User
    @PutMapping("/updateUser")
    public MessageResponse updateUser(@RequestBody SetupUserEntity setupUserEntity) {
        return setupUserService.updateUser(setupUserEntity);
    }

    //Done -
    //Delete User  By User ID
    @DeleteMapping("/deleteUserById/{id}")
    public MessageResponse deleteUser(@PathVariable Long id)    {
        return setupUserService.deleteUser(id);
    }

    //Done - param- [{"":""},{"":""}]
    //update All User As an array - multiple rows
    @PutMapping("/updateAllUser")
    public List<SetupUserEntity> updateAllUser(@RequestBody List<SetupUserEntity> setupUserEntity) {
        return setupUserService.updateAllUser(setupUserEntity);
    }



}
