package com.example.carrent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CarActivity extends AppCompatActivity {

    String carid;

    TextView carnameTV;
    TextView modelTV;

    TextView rateTv;
    ImageView carIV;

    TextView transmissiontv;
    TextView fuelTV;
    TextView seatTV;

    Button rentbutton;


    String rate;


    private Globalpreference globalpreference;
    String ip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        globalpreference = new Globalpreference(this);
        ip = globalpreference.getIP();

        carid = getIntent().getStringExtra("carid");

        carnameTV = findViewById(R.id.CarnameTV);
        modelTV = findViewById(R.id.ModelTV);
        rateTv = findViewById(R.id.CarRateTV);
        carIV =findViewById(R.id.CarImageView);
        transmissiontv = findViewById(R.id.cartransmissionTV);
        fuelTV = findViewById(R.id.carfueltypeTV);
        seatTV = findViewById(R.id.carseatsTV);
        rentbutton = findViewById(R.id.RentButton);

        rentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CarActivity.this,BookingActivity.class);
                intent.putExtra("carid",carid);
                intent.putExtra("amount",rate);
                startActivity(intent);
            }
        });


       carIV.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(CarActivity.this, ""+carid, Toast.LENGTH_SHORT).show();
           }
       });

       cardetails();

    }

    private void cardetails() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://" + ip + "/car_rent/api/viewcardetails.php?cid=" + carid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("failed")) {
                    Toast.makeText(CarActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String name = object.getString("name");
                            String model = object.getString("model");
                            rate = object.getString("rate");
                            String image = object.getString("image");
                            String transmission = object.getString("transmission_type");
                            String fuel = object.getString("fuel_type");
                            String seats = object.getString("no_of_seats");

                            carnameTV.setText(name);
                            modelTV.setText(model);
                            rateTv.setText("₹"+rate);
                            transmissiontv.setText(transmission);
                            fuelTV.setText(fuel);
                            seatTV.setText(seats);

                            Glide.with(CarActivity.this).load("http://" + ip + "/car_rent/car_tbl/uploads/" + image).into(carIV);


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CarActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(CarActivity.this);
        requestQueue.add(stringRequest);



    }
}