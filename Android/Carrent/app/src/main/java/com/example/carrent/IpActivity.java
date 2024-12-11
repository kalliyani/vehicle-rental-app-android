package com.example.carrent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IpActivity extends AppCompatActivity {


    EditText ipet;
    Button submitbt;

    private Globalpreference globalpreference;
    String ip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);


        globalpreference = new Globalpreference(this);
        ip = globalpreference.getIP();

        ipet = findViewById(R.id.ipET);
        submitbt = findViewById(R.id.Submitbutton);

        ipet.setText(globalpreference.getIP());

        submitbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                globalpreference.saveip(ipet.getText().toString());
                Intent intent = new Intent(IpActivity.this,SplashActivity.class);
                startActivity(intent);
            }
        });

    }
}