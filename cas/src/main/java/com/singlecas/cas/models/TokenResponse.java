package com.singlecas.cas.models;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class TokenResponse {
    private boolean success;
    private String token;
    private String refreshToken;
    private String name;
    private String brandId;
    private List<String> roles;
}
