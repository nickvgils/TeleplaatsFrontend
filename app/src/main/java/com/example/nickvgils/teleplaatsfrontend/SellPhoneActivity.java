package com.example.nickvgils.teleplaatsfrontend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SellPhoneActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextView brandText, modelText, imeiText, priceText;
    private Spinner statusSpinner;

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

        brandText = findViewById(R.id.BrandInput);
        modelText = findViewById(R.id.ModelInput);
        imeiText = findViewById(R.id.ImeiInput);

        statusSpinner = findViewById(R.id.StatusDropdown);
        priceText = findViewById(R.id.PriceInput);



        sellPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String price = priceText.getText().toString();

                Integer priceInt = Integer.parseInt(price.equals("") ? "0" : price);
                Phone phone = new Phone(imeiText.getText().toString(), modelText.getText().toString(), brandText.getText().toString(), statusSpinner.getSelectedItem().toString(), "", "", priceInt, false );

//
                new Web3J.SellPhoneTask().execute(phone);
//
                Toast.makeText(v.getContext(), "Phone succesfully added to the market!", Toast.LENGTH_LONG).show();
                onBackPressed();
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
