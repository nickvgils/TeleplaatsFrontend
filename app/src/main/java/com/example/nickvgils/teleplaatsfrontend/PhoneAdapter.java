package com.example.nickvgils.teleplaatsfrontend;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyViewHolder> {

    private List<Phone> phoneList;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView brandModel, price, bidding, owner;

        public MyViewHolder(View view)
        {
            super(view);
            brandModel = view.findViewById(R.id.BrandModel);
            price = view.findViewById(R.id.Price);
            bidding = view.findViewById((R.id.Bidding));
            owner = view.findViewById(R.id.Owner);
        }
    }

    public PhoneAdapter(List<Phone> phoneList)
    {
        this.phoneList = phoneList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.phone_list_row,viewGroup,false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Phone phone = phoneList.get(position);
        holder.brandModel.setText(phone.getBrand() +" - " + phone.getModel());
        holder.price.setText(String.valueOf(phone.getPrice()));
        if(phone.isBidding())
            holder.bidding.setText("Bidding: YES");
        else
            holder.bidding.setText("Bidding: NO");

        holder.owner.setText(phone.getUsername());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "CLICKED ON: " + position);
                Intent intent = new Intent(v.getContext(),DetailedOffer.class);

                intent.putExtra("brand", phoneList.get(position).getBrand());
                intent.putExtra("model", phoneList.get(position).getModel());
                intent.putExtra("price", phoneList.get(position).getPrice());
                intent.putExtra("imei", phoneList.get(position).getImei());

                switch (phoneList.get(position).getStatus())
                {
                    case NEW:
                    {
                        intent.putExtra("status", "NEW");
                    }   break;
                    case USED:
                    {
                        intent.putExtra("status", "USED");
                    }   break;
                    case BROKEN:
                    {
                        intent.putExtra("status", "BROKEN");
                    }   break;
                    case UNDEFINED:
                    {
                        intent.putExtra("status", "UNDEFINED");
                    }   break;
                }

                v.getContext().startActivity(intent);
            }
        });
    }




    @Override
    public int getItemCount() {
        return phoneList.size();
    }
}
