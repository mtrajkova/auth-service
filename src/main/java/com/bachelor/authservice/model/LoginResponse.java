package com.bachelor.authservice.model;

public class LoginResponse {
    private String token;
    private String username;
    private Boolean isAdmin;
    private Boolean isCreator;

    public LoginResponse(String token, String username, Boolean isAdmin, Boolean isCreator) {
        this.token = token;
        this.username = username;
        this.isAdmin = isAdmin;
        this.isCreator = isCreator;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public Boolean getCreator() {
        return isCreator;
    }
}
