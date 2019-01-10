package com.example.nickvgils.teleplaatsfrontend;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyPhonesAdapter extends RecyclerView.Adapter<MyPhonesAdapter.MyViewHolderMyPhones>{

    private List<Phone> myPhones;

    public class MyViewHolderMyPhones extends RecyclerView.ViewHolder
    {
        public TextView myPhoneName, myPhoneImei;

        public MyViewHolderMyPhones(View view)
        {
            super(view);

            myPhoneName = view.findViewById(R.id.brandModelMyPhones);
            myPhoneImei = view.findViewById(R.id.imeiMyPhones);
        }
    }

    public MyPhonesAdapter(List<Phone> myPhones)
    {
        this.myPhones = myPhones;
    }

    @NonNull
    @Override
    public MyViewHolderMyPhones onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_phones_list_row,viewGroup, false);

        return new MyViewHolderMyPhones(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderMyPhones myViewHolderAdapter, int i) {
        Phone phone = myPhones.get(i);
        myViewHolderAdapter.myPhoneName.setText(phone.getBrand() + " - " + phone.getModel());
        myViewHolderAdapter.myPhoneImei.setText(phone.getImei());

    }

    @Override
    public int getItemCount() {
        return myPhones.size();
    }
}
