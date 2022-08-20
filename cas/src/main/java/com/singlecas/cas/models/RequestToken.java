package com.singlecas.cas.models;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class RequestToken {
    private String token;
}
