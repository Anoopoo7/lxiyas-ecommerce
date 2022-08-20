package com.users.userMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users.userMicroservice.models.Users;
import com.users.userMicroservice.services.UserserviceV1;

@RestController
@RequestMapping("/v1/users")
public class UsercontrollerV1 {

    @Autowired
    private UserserviceV1 userserviceV1;
    @PostMapping()
    public Users getUserByEmailAndPasswordAndActive() {
        return userserviceV1.getUserByEmailAndPasswordAndActive();
    }

}