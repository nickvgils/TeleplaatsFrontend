package com.example.nickvgils.teleplaatsfrontend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailedOffer extends AppCompatActivity {

    private String brand;
    private String model;
    private String status;
    private String price;
    private String imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_offer);
    }
}
