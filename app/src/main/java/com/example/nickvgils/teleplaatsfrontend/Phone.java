package com.example.nickvgils.teleplaatsfrontend;

import android.support.annotation.NonNull;

public class Phone {

    public enum Status{NEW,USED,BROKEN,UNDEFINED}

    private String brand;
    private String model;
    private Status status;
    private String imei;
    private int price;
    private String username;
    private boolean bidding;
    private String ownerAddr;


    public Phone(String imei, String model, String brand, String status, String username, String ownerAddr, int price, boolean bidding) {
        this.brand = brand;
        this.model = model;
        this.imei = imei;
        this.price = price;
        this.username = username;
        this.bidding = bidding;
        this.ownerAddr = ownerAddr;

        switch (status.toLowerCase())
        {
            case "new": this.status = Status.NEW; break;
            case "used": this.status = Status.USED; break;
            case "broken" : this.status = Status.BROKEN; break;
            default: this.status = Status.UNDEFINED; break;
        }
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getOwnerAddr() {
        return ownerAddr;
    }

    public void setOwnerAddr(String ownerAddr) {
        this.ownerAddr = ownerAddr;
    }
}
