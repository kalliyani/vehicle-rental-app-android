package com.example.carrent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    TextView nametv;
    TextView contacttv;
    TextView emailtv;
    ImageView editProfileIV;
    ImageView logImageView;

    private Globalpreference globalpreference;
    String ip,userid;
    String userdata;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        globalpreference = new Globalpreference(this);
        ip = globalpreference.getIP();
        userid = globalpreference.getID();



        nametv = findViewById(R.id.nameTV);
        contacttv = findViewById(R.id.contactTV);
        emailtv = findViewById(R.id.emailTV);
        editProfileIV = findViewById(R.id.editProfileImageView);
        logImageView = findViewById(R.id.logoutImageView);

        logImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileActivity.this,HomeActivity.class);
                startActivity(intent);

            }
        });

        editProfileIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileActivity.this,EditprofileActivity.class);
                intent.putExtra("userdata",userdata);
                startActivity(intent);

            }
        });

        getprofile();


    }

    private void getprofile() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/car_rent/api/profile.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!response.equals("")) {
                    try {

                        userdata = response;

                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        JSONObject data = jsonArray.getJSONObject(0);

                        String name = data.getString("name");
                        String number = data.getString("number");
                        String email = data.getString("email");


                        nametv.setText(name);
                        contacttv.setText(number);
                        emailtv.setText(email);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileActivity.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            @Nullable
            protected Map<String ,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id",userid);
                return params;
            }};

        RequestQueue requestQueue = Volley.newRequestQueue(ProfileActivity.this);
        requestQueue.add(stringRequest);



    }
}