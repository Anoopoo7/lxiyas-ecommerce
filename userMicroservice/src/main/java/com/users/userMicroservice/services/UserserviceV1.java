package com.users.userMicroservice.services;

import org.springframework.stereotype.Service;

import com.users.userMicroservice.models.Users;

@Service
public class UserserviceV1 {

    public Users getUserByEmailAndPasswordAndActive() {
        Users user = new Users();
        user.setFirstName("anoop");
        user.setLastName("ss");
        user.setPassword("abcedf");
        return user;
    }

}
