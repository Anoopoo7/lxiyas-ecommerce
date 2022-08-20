package com.singlecas.cas.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.singlecas.cas.models.Users;

@Service
public class Userservice {
    public Users getUser() {
        return new Users("anoop", "anoop@123", "1234567890", new ArrayList<>());
    }
}
