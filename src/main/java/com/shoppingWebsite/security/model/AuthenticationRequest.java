package com.shoppingWebsite.security.model;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {
    private Long userId;
    private String username;
    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //need default constructor for JSON Parsing
    public AuthenticationRequest() {}

    public AuthenticationRequest(Long userId,String username, String password) {
        this.setUserId(userId);
        this.setUsername(username);
        this.setPassword(password);
    }
}
