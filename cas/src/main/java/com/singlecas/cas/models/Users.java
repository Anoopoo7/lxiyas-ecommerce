package com.singlecas.cas.models;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private String name;
    private String password;
    private String brandId;
    private List<String> roles;
}
