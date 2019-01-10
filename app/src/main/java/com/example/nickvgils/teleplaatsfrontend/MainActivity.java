package com.example.nickvgils.teleplaatsfrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.math.BigInteger;
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
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tuples.generated.Tuple8;

import java.io.IOException;

import static org.web3j.tx.Contract.GAS_LIMIT;
import static org.web3j.tx.ManagedTransaction.GAS_PRICE;

public class MainActivity extends AppCompatActivity implements Web3jInterface {

    private String TAG = "MAINACTIVITY";
    private Button sellPhoneButton;
    private List<Phone> phoneList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PhoneAdapter mAdapter;

    public Web3J web3J;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web3J = Web3J.getInstance();



        sellPhoneButton = findViewById(R.id.sellPhoneButton);
        sellPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SellPhoneActivity.class);
                startActivity(intent);
            }
        });

        Button refreshButton = findViewById(R.id.RefreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPhones();
            }
        });

        //Recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.PhoneRecyclerView);

        mAdapter = new PhoneAdapter(phoneList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        initContractTask();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPhones();
    }

    public void initContractTask(){
        new Web3J.InitContractTask(this).execute();
    }

    public void getPhones(){
        new Web3J.GetPhonesTask(this).execute();
    }

    public void createTestPhones()
    {
        phoneList.add(new Phone("12345","IPhone X","Apple","broken", "Camiel", "tilly", 900, false));
        phoneList.add(new Phone("12345","IPhone X","Apple","broken", "Camiel", "tilly", 900, false));
        phoneList.add(new Phone("12345","IPhone X","Apple","broken", "Camiel", "tilly", 900, false));
        phoneList.add(new Phone("12345","IPhone X","Apple","broken", "Camiel", "tilly", 900, false));
        phoneList.add(new Phone("12345","IPhone X","Apple","broken", "Camiel", "tilly", 900, false));

        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void refreshPhones(List<Phone> phones)
    {
        phoneList.clear();
        phoneList.addAll(phones);
        mAdapter.notifyDataSetChanged();
    }
}
