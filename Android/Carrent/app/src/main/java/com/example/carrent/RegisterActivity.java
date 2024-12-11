package com.example.carrent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {


    EditText nameET;
    EditText numberET;
    EditText emailET;
    EditText passwordET;
    Button registerBT;
    TextView signInTV;

    private Globalpreference globalpreference;
    String ip;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        globalpreference = new Globalpreference(this);
        ip = globalpreference.getIP();


        nameET = findViewById(R.id.nameEditText);
        numberET = findViewById(R.id.numberEditText);
        emailET = findViewById(R.id.emailEditText);
        passwordET = findViewById(R.id.passwordEditText);
        registerBT = findViewById(R.id.registerButton);
        signInTV = findViewById(R.id.signinTextView);

        signInTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });



        registerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              check();

            }
        });



    }

    private void check() {
        if (nameET.getText().toString().equals("")){
            nameET.setError("please enter name");
        } else if (numberET.getText().toString().equals("")||numberET.getText().length()>10||numberET.getText().length()<10) {
            numberET.setError("Invalid phone number");
        } else if (emailET.getText().toString().equals("")) {
            emailET.setError("Please enter email");

        } else if (passwordET.getText().toString().equals("")) {
            passwordET.setError("please enter");

        }else {
            register();
        }
    }

    private void register() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/car_rent/api/register.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                if (response.equals("success")) {
                    Toast.makeText(RegisterActivity.this, "registration success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            @Nullable
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name",nameET.getText().toString());
                params.put("number",numberET.getText().toString());
                params.put("email",emailET.getText().toString());
                params.put("password",passwordET.getText().toString());
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        requestQueue.add(stringRequest);


    }}




