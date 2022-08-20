package com.singlecas.cas.models;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AuthentificationRequest {
    private String brandId;
    private String userName;
    private String password;
    private String grandType;
}
