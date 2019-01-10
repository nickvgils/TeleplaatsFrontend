package com.example.nickvgils.teleplaatsfrontend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyPhones extends AppCompatActivity {

    Button returnButton;

    private RecyclerView myPhonesRecyclerView;
    private MyPhonesAdapter myPhonesAdapter;
    private List<Phone> myPhones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_phones);

        returnButton = findViewById(R.id.backButtonMyPhones);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //recylcerview my phones
        myPhonesRecyclerView = findViewById(R.id.recycleViewMyPhones);

        myPhones = new ArrayList<Phone>();
        myPhones.add(new Phone("12345","IPhone X","Apple","broken", "Camiel", "tilly", 900, false));
        myPhones.add(new Phone("12345","IPhone X","Apple","broken", "Camiel", "tilly", 900, false));
        myPhones.add(new Phone("12345","IPhone X","Apple","broken", "Camiel", "tilly", 900, false));


        myPhones.clear();
        if(Web3J.phones != null)
        {
            for(Phone p : Web3J.phones)
            {
                if(p.getOwnerAddr().equals(Web3J.ownAddr))
                {
                    myPhones.add(p);
                }
            }
        }



        myPhonesAdapter = new MyPhonesAdapter(myPhones);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager((getApplicationContext()));
        myPhonesRecyclerView.setLayoutManager(mLayoutManager);
        myPhonesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myPhonesRecyclerView.setAdapter(myPhonesAdapter);

        myPhonesAdapter.notifyDataSetChanged();


    }
}
