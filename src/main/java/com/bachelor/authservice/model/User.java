package com.bachelor.authservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull(message = "Email must be present")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    private String username;
    @NotNull(message = "Password must be present")
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotNull(message = "Name must be present")
    @NotBlank(message = "Name cannot be blank")
    private String fullName;
    private Boolean admin = false;
    private Boolean creator = false;
    private String phoneNumber;
    private String imageLocation = "./assets/img/placeholder.jpg";

    public User() {
    }

    public User(Long id, String username, String password, Boolean admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getCreator() {
        return creator;
    }

    public void setCreator(Boolean creator) {
        this.creator = creator;
    }
}
