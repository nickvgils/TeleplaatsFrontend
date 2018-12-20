package com.example.nickvgils.teleplaatsfrontend;

import android.support.annotation.NonNull;

public class Phone {

    public enum Status{NEW,USED,BROKEN}

    private String brand;
    private String model;
    private Status status;
    private String imei;
    private int price;
    private String owner;
    private boolean bidding;

    public Phone(String brand, String model, Status status, String imei, int price, String owner, boolean bidding) {
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.imei = imei;
        this.price = price;
        this.owner = owner;
        this.bidding = bidding;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isBidding() {
        return bidding;
    }

    public void setBidding(boolean bidding) {
        this.bidding = bidding;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }


}
