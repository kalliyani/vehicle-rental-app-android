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
import com.example.carrent.Adapter.RentalAdapter;
import com.example.carrent.ModelClass.Rental_Modelclass;
import com.example.carrent.ModelClass.Vehiclebooking_Modelclass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RentalActivity extends AppCompatActivity {

    RecyclerView rentalRV;
    ArrayList<Rental_Modelclass> list;
    private Globalpreference globalpreference;
    String ip,uid;
    ImageView BackImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental);

        globalpreference = new Globalpreference(this);
        ip = globalpreference.getIP();
        uid = globalpreference.getID();

       rentalRV = findViewById(R.id.RentalRV);

        TextView title = findViewById(R.id.appBarTitle);
        title.setText(" Rental History ");
        BackImageButton = findViewById(R.id.BackImageButton);
        BackImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RentalActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rentalRV.setLayoutManager(layoutManager);

        Rentalhistory();
    }

    private void Rentalhistory() {

        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://"+ip+"/car_rent/api/viewrentals.php?uid="+uid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("failed")) {

                    Toast.makeText(RentalActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                } else {
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
                            String amount = object.getString("amount");
                            String status = object.getString("status");
                            String image = object.getString("carimage");

                            list.add(new Rental_Modelclass(id, carname, carmodel, booking_date, days,amount,status,image));

                            RentalAdapter adapter = new RentalAdapter(list,RentalActivity.this);
                            rentalRV.setAdapter(adapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RentalActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(RentalActivity.this);
        requestQueue.add(stringRequest);



    }
}