package com.example.ana.socketclient.model;

import com.google.gson.annotations.SerializedName;

public class Announcement {

    @SerializedName("id")
    private Integer id;
    @SerializedName("cnpj")
    private String cnpj;
    @SerializedName("announcement")
    private String announcement;

    public Announcement(Integer id, String cnpj, String announcement) {
        this.id = id;
        this.cnpj = cnpj;
        this.announcement = announcement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }
}

