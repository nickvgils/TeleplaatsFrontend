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

    private String TAG = "MAINACTIVITY";
    private Button sellPhoneButton;
    private Button myPhonesButton;
    public static List<Phone> phoneList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PhoneAdapter mAdapter;
    private Web3J web3J;
    public String ownAddr;

    private Teleplaats teleplaats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPhonesButton = findViewById(R.id.MyPhonesButton);
        myPhonesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyPhones.class);
                intent.putExtra("ownAddr", ownAddr);
                startActivity(intent);
            }
        });


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

        //web3J = Web3J.getInstance();

        new DeployWeb3jContract().execute();

        //new RetreiveWeb3jData().execute();

//        //testcode camiel
//        Intent intent = new Intent(this,SellPhoneActivity.class);
//        startActivity(intent);

    }


    public String getOwnAddr() {
        return ownAddr;
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

    class DeployWeb3jContract extends AsyncTask<Void, Void, Teleplaats> {

        @Override
        protected Teleplaats doInBackground(Void... voids) {

            try {
                //Teleplaats contract = Teleplaats.deploy(
                //        Web3J.getInstance().web3, Web3J.getCredentialsFromPrivateKey(),
                //        GAS_PRICE, GAS_LIMIT).send();

                Teleplaats contractLoad = Teleplaats.load("0x5756268577876592AF08663ba3Ff5bD45054CE32",Web3J.getInstance().web3,Web3J.getCredentialsFromPrivateKey(),GAS_PRICE, GAS_LIMIT);
                return contractLoad;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Teleplaats contract) {
            teleplaats = contract;
            Log.d(TAG, "onPostExecute: " + teleplaats.getContractAddress());
            ownAddr = teleplaats.getContractAddress();
            new AddPhone().execute();


        }
    }

    class GetAllPhones extends AsyncTask<Void, Void, List<Phone>> {

        @Override
        protected List<Phone> doInBackground(Void... voids) {

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

    class AddPhone extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                //TransactionReceipt tr = teleplaats.sellPhone("henkie", "13456", "Iphone", "Apple", "broKen", BigInteger.valueOf(152),false).send();
                Log.d(TAG, "doInBackground: ");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            Log.d(TAG, "onPostExecute: " + s);

            new GetAllPhones().execute();
        }
    }



}
