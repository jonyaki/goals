package com.jonathan.goals.models;

import jakarta.validation.constraints.NotEmpty;

public class LoginRequest {
    @NotEmpty(message = "username es necesario para generar el JWT")
    private String username;
    private String password;


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
}
