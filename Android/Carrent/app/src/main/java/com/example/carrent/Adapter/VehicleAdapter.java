package com.example.carrent.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.carrent.CarActivity;
import com.example.carrent.Globalpreference;
import com.example.carrent.ModelClass.Vehicledetails_Modelclass;
import com.example.carrent.R;

import java.util.ArrayList;

public class VehicleAdapter  extends RecyclerView.Adapter<VehicleAdapter.MYViewHolder> {

    ArrayList<Vehicledetails_Modelclass> list;
    Context context;

    String ip;
   Globalpreference globalpreference;

    public VehicleAdapter(ArrayList<Vehicledetails_Modelclass>list,Context context){
        this.list = list;
        this.context = context;

        globalpreference = new Globalpreference(context);
        ip = globalpreference.getIP();
    }



    @NonNull
    @Override
    public MYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_viewvehicles,parent,false);
        return new  MYViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VehicleAdapter.MYViewHolder holder, int position) {


        Vehicledetails_Modelclass carList = list.get(position);


        holder.vehiclenameTV.setText(carList.getName());
        holder.vehiclemodelTV.setText(carList.getModel());
        holder.locationTV.setText(carList.getLocation());
        holder.vehiclerate.setText("₹ "+carList.getRate());
      //  holder.vehiclestatus.setText(userlist.getStatus());


        Glide.with(context).load("http:"+ip+"/car_rent/car_tbl/uploads/"+carList.getImage()).into(holder.vehicleIV);


        holder.seeLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,CarActivity.class);
                intent.putExtra("carid",carList.getId());
                context.startActivity(intent);

               // Toast.makeText(context, ""+carList.getId(), Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MYViewHolder extends RecyclerView.ViewHolder{

       LinearLayout seeLL;
        TextView  vehiclenameTV;
        TextView vehiclemodelTV;
        TextView locationTV;
        ImageView  vehicleIV;
        TextView   vehiclerate;
        TextView  vehiclestatus;

        public MYViewHolder(@NonNull View itemView){
            super(itemView);

          seeLL = itemView.findViewById(R.id.seemoreLL);
           vehiclenameTV = itemView.findViewById(R.id.VehicleNameTV);
            vehiclemodelTV = itemView.findViewById(R.id.VehicleModelTV);
            locationTV = itemView.findViewById(R.id.LocationTV);
            vehicleIV = itemView.findViewById(R.id.VehicleIV);
            vehiclerate = itemView.findViewById(R.id.VehicleRateTV);
            vehiclestatus = itemView.findViewById(R.id.VehicleStatusTV);



        }



    }
}
