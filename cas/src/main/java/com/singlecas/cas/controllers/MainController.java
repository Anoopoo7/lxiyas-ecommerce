package com.singlecas.cas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.singlecas.cas.models.AuthentificationRequest;
import com.singlecas.cas.models.RequestToken;
import com.singlecas.cas.services.Authservices;

@RestController
@RequestMapping("/v1/auth")
public class MainController {
    @Autowired
    private Authservices authservices;

    @PostMapping("/token")
    public ResponseEntity<?> authentification(@RequestBody AuthentificationRequest authentificationRequest) {
        return ResponseEntity.ok(authservices.generateToken(authentificationRequest));
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody RequestToken requestToken) {
        return ResponseEntity.ok(authservices.validateToken(requestToken.getToken()));
    }
}
