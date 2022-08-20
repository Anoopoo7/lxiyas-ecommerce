package com.users.userMicroservice.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
// @Document("")
public class Users {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean active;
    private boolean enabled;
    private boolean verified;
    private String gender;
    private boolean firstLoggedIn;
    private boolean firstOrder;
    private boolean premium;
    private Date createdDate;
    private String password;
    private Date updatedDate;
    private Map<String, String> token;
    private List<String> roles;
    private List<String> addressIds;
    private List<String> cardIds;
    private String brand;
}
