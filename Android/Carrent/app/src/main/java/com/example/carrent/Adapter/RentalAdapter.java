package com.example.carrent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.carrent.Globalpreference;
import com.example.carrent.ModelClass.Rental_Modelclass;
import com.example.carrent.ModelClass.Vehiclebooking_Modelclass;
import com.example.carrent.R;

import java.util.ArrayList;

public class RentalAdapter extends RecyclerView.Adapter<RentalAdapter.MYViewHolder> {


    ArrayList<Rental_Modelclass> list;
    Context context;
    String ip;

    Globalpreference globalpreference;

    public RentalAdapter(ArrayList<Rental_Modelclass> list,Context context){
        this.list = list;
        this.context = context;

        globalpreference = new Globalpreference(context);
        ip = globalpreference.getIP();
    }


    @NonNull
    @Override
    public RentalAdapter.MYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_rentals,parent,false);
        return new MYViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RentalAdapter.MYViewHolder holder, int position) {

        Rental_Modelclass rentallist = list.get(position);

        holder.carnametv.setText(rentallist.getCarname());
        holder.carmodeltv.setText(rentallist.getCarmodel());
        holder.bookingdatetv.setText(rentallist.getBooking_date());
        holder.noofdaystv.setText(rentallist.getNoofdays());
        holder.amounttv.setText("₹ "+rentallist.getAmount());
        holder.statustv.setText(rentallist.getStatus());

        Glide.with(context).load("http:/"+ip+"/car_rent/car_tbl/uploads/"+rentallist.getImage()).into(holder.imageIV);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MYViewHolder extends RecyclerView.ViewHolder{

        TextView carnametv;
        TextView carmodeltv;
        TextView bookingdatetv;
        TextView noofdaystv;

        TextView amounttv;
        TextView statustv;
        ImageView imageIV;

        public MYViewHolder(@NonNull View itemview){
            super(itemview);

            carnametv= itemview.findViewById(R.id.RCarNameTextView);
            carmodeltv = itemview.findViewById(R.id.RCarModelTextView);
            bookingdatetv = itemview.findViewById(R.id.RBookingDateTextView);
            noofdaystv = itemview.findViewById(R.id.RNoOfDaysTextView);
            amounttv = itemview.findViewById(R.id.RAmountTV);
            statustv = itemview.findViewById(R.id.RStatusTextView);
            imageIV = itemview.findViewById(R.id.RCarImageView);

        }
    }

}
