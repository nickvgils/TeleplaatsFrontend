package com.example.nickvgils.teleplaatsfrontend;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyPhonesAdapter extends RecyclerView.Adapter<MyPhonesAdapter.MyViewHolderMyPhones>{

    private List<Phone> myPhones;

    public class MyViewHolderMyPhones extends RecyclerView.ViewHolder
    {
        public TextView myPhoneOfferName, myPhoneOfferValue, myPhoneName, myPhoneImei;

        public MyViewHolderMyPhones(View view)
        {
            super(view);

            //todo views ophalen
        }
    }

    public MyPhonesAdapter(List<Phone> myPhones)
    {
        this.myPhones = myPhones;
    }

    @NonNull
    @Override
    public MyViewHolderMyPhones onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderMyPhones myViewHolderAdapter, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
