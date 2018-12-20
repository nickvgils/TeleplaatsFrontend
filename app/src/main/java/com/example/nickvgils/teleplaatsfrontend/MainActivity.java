package com.example.nickvgils.teleplaatsfrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;




import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MAINACTIVITY";private Button sellPhoneButton;
    private List<Phone> phoneList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PhoneAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sellPhoneButton = findViewById(R.id.sellPhoneButton);
        sellPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SellPhoneActivity.class);
                startActivity(intent);
            }
        });

        //Recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.PhoneRecyclerView);

        mAdapter = new PhoneAdapter(phoneList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        createTestPhones();




        //new RetreiveWeb3jData().execute();

//        //testcode camiel
//        Intent intent = new Intent(this,SellPhoneActivity.class);
//        startActivity(intent);

    }


    public void createTestPhones()
    {
        phoneList.add(new Phone("Apple","IPhone X", Phone.Status.BROKEN, "12345", 900, "Henkie", false));
        phoneList.add(new Phone("Samsung","Note 8 edge", Phone.Status.NEW, "97846513", 458, "Jappie", true));
        phoneList.add(new Phone("OnePlus","TWo", Phone.Status.USED, "645", 84569, "Ons Miranda", false));
        phoneList.add(new Phone("Apple","IPod touch", Phone.Status.NEW, "68456845", 5, "pieter", false));
        phoneList.add(new Phone("Apple","IPhone X", Phone.Status.BROKEN, "12345", 900, "Henkie", false));
        phoneList.add(new Phone("Samsung","Note 8 edge", Phone.Status.NEW, "97846513", 458, "Jappie", true));
        phoneList.add(new Phone("OnePlus","TWo", Phone.Status.USED, "645", 84569, "Ons Miranda", false));
        phoneList.add(new Phone("Apple","IPod touch", Phone.Status.NEW, "68456845", 5, "pieter", false));
        phoneList.add(new Phone("Apple","IPhone X", Phone.Status.BROKEN, "12345", 900, "Henkie", false));
        phoneList.add(new Phone("Samsung","Note 8 edge", Phone.Status.NEW, "97846513", 458, "Jappie", true));
        phoneList.add(new Phone("OnePlus","TWo", Phone.Status.USED, "645", 84569, "Ons Miranda", false));
        phoneList.add(new Phone("Apple","IPod touch", Phone.Status.NEW, "68456845", 5, "pieter", false));

        mAdapter.notifyDataSetChanged();

    }     
}
