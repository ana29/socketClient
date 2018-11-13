package com.example.ana.socketclient.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;
    @SerializedName("address")
    private String address;
    @SerializedName("cpf")
    private String cpf;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("job")
    private String job;
    @SerializedName("role")
    private String role;
    @SerializedName("cnpj")
    private String cnpj;
    @SerializedName("token")
    private String token;

    public User(String name, String phone, String address, String cpf, String email,
                String password, String job, String role, String cnpj, String token) {

        this.name = name;
        this.phone = phone;
        this.address = address;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.job = job;
        this.role = role;
        this.cnpj = cnpj;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
