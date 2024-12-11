package com.example.carrent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carrent.Adapter.VehicleAdapter;
import com.example.carrent.ModelClass.Vehicledetails_Modelclass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewVehiclesActivity extends AppCompatActivity {



    RecyclerView vehicleRV;
    ArrayList<Vehicledetails_Modelclass> list;
    private Globalpreference globalpreference;
    String ip;
    EditText locationet;
    Button searchbutton;
    TextView novehicleTV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vehicles);



        globalpreference = new Globalpreference(this);
        ip = globalpreference.getIP();

        vehicleRV = findViewById(R.id.VehicleRV);
        novehicleTV = findViewById(R.id.NoVehicleTV);
        locationet=findViewById(R.id.locationET);
        searchbutton = findViewById(R.id.searchbtn);




        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchveh();

            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        vehicleRV.setLayoutManager(layoutManager);


        vehicledetails();







    }

    private void searchveh() {

        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/car_rent/api/location.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               //  Toast.makeText(ViewVehiclesActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                if (response.equals("failed")){
                   // Toast.makeText(ViewVehiclesActivity.this, "No Vehicle Available", Toast.LENGTH_SHORT).show();

                    vehicleRV.setVisibility(View.GONE);
                    novehicleTV.setVisibility(View.VISIBLE);
                }else {
                    novehicleTV.setVisibility(View.GONE);
                    vehicleRV.setVisibility(View.VISIBLE);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i <jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String name = object.getString("name");
                            String model = object.getString("model");
                            String location= object.getString("location");
                            String image = object.getString("image");
                            String rate = object.getString("rate");
                            String status = object.getString("status");

                            list.add(new Vehicledetails_Modelclass(id, name, model,location, image, rate, status));

                            VehicleAdapter adapter = new VehicleAdapter(list,ViewVehiclesActivity.this);
                            vehicleRV.setAdapter(adapter);

                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewVehiclesActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            @Nullable
            protected Map<String,String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("location",locationet.getText().toString());
                return params;


            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ViewVehiclesActivity.this);
        requestQueue.add(stringRequest);


    }

    private void vehicledetails() {


        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://" + ip + "/car_rent/api/viewvehicledetails.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("failed")) {

                    Toast.makeText(ViewVehiclesActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String name = object.getString("name");
                            String model = object.getString("model");
                            String location= object.getString("location");
                            String image = object.getString("image");
                            String rate = object.getString("rate");
                            String status = object.getString("status");

                            list.add(new Vehicledetails_Modelclass(id,name,model,location,image,rate,status));

                           VehicleAdapter adapter = new VehicleAdapter(list,ViewVehiclesActivity.this);
                           vehicleRV.setAdapter(adapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewVehiclesActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(ViewVehiclesActivity.this);
        requestQueue.add(stringRequest);


    }
}