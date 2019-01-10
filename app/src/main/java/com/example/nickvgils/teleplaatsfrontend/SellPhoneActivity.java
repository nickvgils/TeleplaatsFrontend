package com.example.nickvgils.teleplaatsfrontend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SellPhoneActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextView brandText, modelText, imeiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_phone);

        SetupSpinner();

        Button cancelButton = findViewById(R.id.cancelButtonSellPhone);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button sellPhoneButton = findViewById(R.id.sellPhoneButton);
        sellPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo sell phone.
            }
        });


    }

    private void SetupSpinner()
    {
        spinner = findViewById(R.id.StatusDropdown);
        List<String> statusOptions = new ArrayList<String>();
        statusOptions.add("New");
        statusOptions.add("Used");
        statusOptions.add("Broken");

        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, statusOptions);
        spinner.setAdapter(statusAdapter);
    }

}
