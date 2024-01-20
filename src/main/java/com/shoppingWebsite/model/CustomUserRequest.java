package com.shoppingWebsite.model;

import java.util.ArrayList;
import java.util.List;

public class CustomUserRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String username;
    private String password;
    private List<CustomItem> favoriteItemList;

    public CustomUserRequest(){};

    public CustomUserRequest(Long id,String firstName, String lastName, String email, String phoneNumber, String address, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.username = username;
        this.password = password;
        this.favoriteItemList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CustomItem> getFavoriteItemList() {
        return favoriteItemList;
    }

    public void setFavoriteItemList(List<CustomItem> favoriteItemList) {
        this.favoriteItemList = favoriteItemList;
    }

    public CustomUser toCustomUser(){
        return new CustomUser(
                this.id,
                this.firstName,
                this.lastName,
                this.email,
                this.phoneNumber,
                this.address,
                this.username,
                this.password
        );
    }
}
