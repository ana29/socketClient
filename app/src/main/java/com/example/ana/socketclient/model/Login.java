package com.example.ana.socketclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Login {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }



}
