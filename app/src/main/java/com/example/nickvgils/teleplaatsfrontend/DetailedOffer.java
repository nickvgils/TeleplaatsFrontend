package com.example.nickvgils.teleplaatsfrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailedOffer extends AppCompatActivity {

    private String brand;
    private String model;
    private String status;
    private String price;
    private String imei;

    private TextView detailedBrandValue, detailedModelValue, detailedStatusValue, detailedImeiValue, detailedPriceValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_offer);

        detailedBrandValue = findViewById(R.id.DetailedBrandValue);
        detailedModelValue = findViewById(R.id.DetailedModelValue);
        detailedStatusValue = findViewById(R.id.DetailedStatusValue);
        detailedImeiValue = findViewById(R.id.DetailedImeiValue);
        detailedPriceValue = findViewById(R.id.DetailedPriceValue);

        Intent intent = getIntent();
        detailedBrandValue.setText(intent.getExtras().getString("brand"));
        detailedModelValue.setText(intent.getExtras().getString("model"));
        detailedPriceValue.setText(intent.getExtras().getInt("price")+"");
        detailedImeiValue.setText(intent.getExtras().getString("imei"));
        detailedStatusValue.setText(intent.getExtras().getString("status"));


        Button buyButton = findViewById(R.id.BuyPhoneButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo add method to buy the phone.
            }
        });

        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}
