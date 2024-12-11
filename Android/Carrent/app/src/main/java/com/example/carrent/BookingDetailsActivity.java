package com.example.carrent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carrent.Adapter.BookingAdapter;
import com.example.carrent.Adapter.VehicleAdapter;
import com.example.carrent.ModelClass.Vehiclebooking_Modelclass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookingDetailsActivity extends AppCompatActivity {

    RecyclerView bookingRV;
    TextView novehicleTV;
    ArrayList<Vehiclebooking_Modelclass>list;
    private Globalpreference globalpreference;

    ImageView BackImageButton;
    String ip,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);


        globalpreference = new Globalpreference(this);
        ip = globalpreference.getIP();
        globalpreference = new Globalpreference(this);
        id = globalpreference.getID();

        bookingRV = findViewById(R.id.carbookingRV);
        novehicleTV = findViewById(R.id.NoBookingTV);

        TextView title = findViewById(R.id.appBarTitle);
        title.setText("Booking History");


        BackImageButton = findViewById(R.id.BackImageButton);
        BackImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingDetailsActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        bookingRV.setLayoutManager(layoutManager);

        bookingdetails();
    }

    private void bookingdetails() {

        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://"+ip+"/car_rent/api/viewBookings.php?uid="+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("failed")) {


                    bookingRV.setVisibility(View.GONE);
                    novehicleTV.setVisibility(View.VISIBLE);

                    //Toast.makeText(BookingDetailsActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                } else {
                    novehicleTV.setVisibility(View.GONE);
                    bookingRV.setVisibility(View.VISIBLE);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String carname = object.getString("carname");
                            String carmodel = object.getString("carmodel");
                            String booking_date = object.getString("booking_date");
                            String days = object.getString("noofdays");
                            String status = object.getString("status");
                            String amount = object.getString("amount");
                            String image = object.getString("carimage");

                            list.add(new Vehiclebooking_Modelclass(id, carname, carmodel, booking_date, days, status,amount,image));

                            BookingAdapter adapter = new BookingAdapter(list,BookingDetailsActivity.this);
                            bookingRV.setAdapter(adapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BookingDetailsActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(BookingDetailsActivity.this);
        requestQueue.add(stringRequest);
    }
}