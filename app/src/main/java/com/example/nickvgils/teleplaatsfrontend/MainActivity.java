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

public class MainActivity extends AppCompatActivity {

    private String TAG = "MAINACTIVITY";private Button sellPhoneButton;
    private List<Phone> phoneList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PhoneAdapter mAdapter;

    private Web3J web3J;

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


        //createTestPhones();

        //web3J = Web3J.getInstance();

        new Web3jTest().execute();

        //new RetreiveWeb3jData().execute();

//        //testcode camiel
//        Intent intent = new Intent(this,SellPhoneActivity.class);
//        startActivity(intent);

    }


//    public void createTestPhones()
//    {
//        phoneList.add(new Phone("Apple","IPhone X", Phone.Status.BROKEN, "12345", 900, "Henkie", false));
//        phoneList.add(new Phone("Samsung","Note 8 edge", Phone.Status.NEW, "97846513", 458, "Jappie", true));
//        phoneList.add(new Phone("OnePlus","TWo", Phone.Status.USED, "645", 84569, "Ons Miranda", false));
//        phoneList.add(new Phone("Apple","IPod touch", Phone.Status.NEW, "68456845", 5, "pieter", false));
//        phoneList.add(new Phone("Apple","IPhone X", Phone.Status.BROKEN, "12345", 900, "Henkie", false));
//        phoneList.add(new Phone("Samsung","Note 8 edge", Phone.Status.NEW, "97846513", 458, "Jappie", true));
//        phoneList.add(new Phone("OnePlus","TWo", Phone.Status.USED, "645", 84569, "Ons Miranda", false));
//        phoneList.add(new Phone("Apple","IPod touch", Phone.Status.NEW, "68456845", 5, "pieter", false));
//        phoneList.add(new Phone("Apple","IPhone X", Phone.Status.BROKEN, "12345", 900, "Henkie", false));
//        phoneList.add(new Phone("Samsung","Note 8 edge", Phone.Status.NEW, "97846513", 458, "Jappie", true));
//        phoneList.add(new Phone("OnePlus","TWo", Phone.Status.USED, "645", 84569, "Ons Miranda", false));
//        phoneList.add(new Phone("Apple","IPod touch", Phone.Status.NEW, "68456845", 5, "pieter", false));
//
//        mAdapter.notifyDataSetChanged();
//
//    }


    class Web3jTest extends AsyncTask<Void, Void, List<Phone>> {

        @Override
        protected List<Phone> doInBackground(Void... voids) {

        Teleplaats contract = deployContract();
        if(contract != null)
        {
            Log.d(TAG, "onPostExecute: " + contract.getContractAddress());

            sellPhone(contract);
            List<Phone> phones = getAllPhones(contract);
            return phones;

        }
        return new ArrayList<Phone>();
        }

        @Override
        protected void onPostExecute(List<Phone> phones) {
            refreshPhones(phones);
        }
    }



    private void refreshPhones(List<Phone> phones)
    {
        phoneList.clear();
        phoneList.addAll(phones);
        mAdapter.notifyDataSetChanged();
    }


    private Teleplaats deployContract(){
        try {
            Teleplaats contract = Teleplaats.deploy(
                    Web3J.getInstance().web3, Web3J.getCredentialsFromPrivateKey(),
                    GAS_PRICE, GAS_LIMIT).send();
            return contract;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Phone> getAllPhones(Teleplaats teleplaats){
        List<Phone> phones = new ArrayList<>();
        try {
            int phoneCount = teleplaats.phoneid().send().intValue();

            for (int i = 1; i <= phoneCount; i++){
                //(String brand, String model, Status status, String imei, int price, String owner, boolean bidding)
                Tuple6<String, String, String, String, String, String> phoneTuple = teleplaats.phones(BigInteger.valueOf(i)).send();
                Tuple8<String, String, Boolean, BigInteger, Boolean, String, String, BigInteger> orderTuple = teleplaats.orders(BigInteger.valueOf(i)).send();
                phones.add(new Phone(phoneTuple.getValue1(), phoneTuple.getValue2(), phoneTuple.getValue3(), phoneTuple.getValue4(), phoneTuple.getValue5(), phoneTuple.getValue6(), orderTuple.getValue4().intValue(), orderTuple.getValue3()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return phones;
    }

    private void sellPhone(Teleplaats teleplaats){
        try {
            TransactionReceipt tr = teleplaats.sellPhone("sellerName", "imei", "model", "brand", "state", BigInteger.valueOf(5),false).send();

            Log.d(TAG, "doInBackground: ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
