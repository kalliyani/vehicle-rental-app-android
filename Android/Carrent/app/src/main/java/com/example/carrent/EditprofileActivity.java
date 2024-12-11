package com.example.carrent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditprofileActivity extends AppCompatActivity {


    EditText nameET;
    EditText numberET;
    EditText emailET;
    TextView submitTV;

    private Globalpreference globalpreference;
    String ip, userid;
    ImageView BackImageButton;

    String intentResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        globalpreference = new Globalpreference(this);
        ip = globalpreference.getIP();
        userid = globalpreference.getID();


        nameET = findViewById(R.id.pNameEditText);
        numberET = findViewById(R.id.pNumberEditText);
        emailET = findViewById(R.id.pEmailEditText);
        submitTV = findViewById(R.id.pSubmitButtonTextView);
        BackImageButton = findViewById(R.id.BackImageButton);
        TextView title = findViewById(R.id.appBarTitle);
        title.setText("Edit Profile");



        intentResponse = getIntent().getStringExtra("userdata");


        setData(intentResponse);

        submitTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }

            {

            }
        });

        BackImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditprofileActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });


    }

    private void setData(String intentResponse) {

        try {
            JSONObject obj = new JSONObject(intentResponse);
            JSONArray array = obj.getJSONArray("data");
            JSONObject data = array.getJSONObject(0);

            String uName = data.getString("name");
            String uNumber = data.getString("number");
            String uEmail = data.getString("email");


            nameET.setText(uName);
            numberET.setText(uNumber);
            emailET.setText(uEmail);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void updateData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/car_rent/api/editprofile.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditprofileActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditprofileActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(EditprofileActivity.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                params.put("name",nameET.getText().toString());
                params.put("number",numberET.getText().toString());
                params.put("email",emailET.getText().toString());
                params.put("uid",userid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }}