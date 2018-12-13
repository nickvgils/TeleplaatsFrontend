package com.example.nickvgils.teleplaatsfrontend;

import android.support.annotation.NonNull;

public class Phone {

    public enum Status{NEW,USED,BROKEN}

    private String brand;
    private String model;
    private Status status;
    private String imei;
    private String description;

    public Phone(String brand, String model, Status status, String imei) {
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.imei = imei;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
