package com.example.carrent.Adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.carrent.BookingDetailsActivity;
import com.example.carrent.Globalpreference;
import com.example.carrent.LoginActivity;
import com.example.carrent.ModelClass.Vehiclebooking_Modelclass;
import com.example.carrent.PaymentActivity;
import com.example.carrent.R;
import com.example.carrent.RegisterActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MYViewHolder> {

    ArrayList<Vehiclebooking_Modelclass>list;
    Context context;
    String ip;

    Globalpreference globalpreference;

    public BookingAdapter(ArrayList<Vehiclebooking_Modelclass> list,Context context){
        this.list = list;
        this.context = context;

        globalpreference = new Globalpreference(context);
        ip = globalpreference.getIP();
    }

    @NonNull
    @Override
    public MYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.carrent.R.layout.raw_bookingdetails,parent,false);
        return new MYViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MYViewHolder holder, int position) {


        Vehiclebooking_Modelclass bookinglist = list.get(position);

        holder.carnameTV.setText(bookinglist.getCarname());
        holder.carmodelTV.setText(bookinglist.getCarmodel());
        holder.bookingdateTV.setText(bookinglist.getBooking_date());
        holder.noofdaysTV.setText(bookinglist.getNoofdays());
        holder.statusTV.setText(bookinglist.getStatus());
        holder.amounttv.setText("₹ "+bookinglist.getAmount());

        if (bookinglist.getStatus().equals("booked")){

            holder.paymentBTN.setVisibility(View.INVISIBLE);
            holder.cancelbtn.setVisibility(View.VISIBLE);

        }else{

            holder.paymentBTN.setVisibility(View.VISIBLE);

        }

       /* holder.cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cancelbooking(bookinglist.getId());
            }
        });*/
        holder.cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an AlertDialog
                new AlertDialog.Builder(context)
                        .setTitle("Cancel Booking")
                        .setMessage("Are you sure you want to cancel this booking?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            // Call the cancelbooking method if confirmed
                            cancelbooking(bookinglist.getId());
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            // Dismiss the dialog
                            dialog.dismiss();
                        })
                        .create()
                        .show();
            }
        });





        holder.paymentBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PaymentActivity.class);
                intent.putExtra("bid",bookinglist.getId());
                intent.putExtra("amount",bookinglist.getAmount());
                context.startActivity(intent);

            }
        });

        Glide.with(context).load("http:/"+ip+"/car_rent/car_tbl/uploads/"+bookinglist.getImage()).into(holder.imageIV);



    }

    private void cancelbooking(String id) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip +"/car_rent/api/cancelbooking.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                if (response.equals("success")) {
                    Toast.makeText(context, "Booking Cancelled", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, BookingDetailsActivity.class);
                    context.startActivity(intent);
                } else {
                    //Toast.makeText(RegisterActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(RegisterActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            @Nullable
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("bid",id);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);



    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MYViewHolder extends RecyclerView.ViewHolder{

        TextView carnameTV;
        TextView carmodelTV;
        TextView bookingdateTV;
        TextView noofdaysTV;
        TextView statusTV;

        TextView amounttv;
         ImageView imageIV;
         Button paymentBTN;
         Button cancelbtn;

        public MYViewHolder(@NonNull View itemview){
            super(itemview);

            carnameTV = itemview.findViewById(R.id.CarNameTextView);
            carmodelTV = itemview.findViewById(R.id.CarModelTextView);
            bookingdateTV = itemview.findViewById(R.id.BookingDateTextView);
            noofdaysTV = itemview.findViewById(R.id.NoOfDaysTextView);
            statusTV = itemview.findViewById(R.id.StatusTextView);
            amounttv = itemview.findViewById(R.id.AmountTV);
            imageIV = itemview.findViewById(R.id.CarImageView);
            paymentBTN = itemview.findViewById(R.id.paymentButton);
            cancelbtn = itemview.findViewById(R.id.CancelButton);
        }



    }
}
