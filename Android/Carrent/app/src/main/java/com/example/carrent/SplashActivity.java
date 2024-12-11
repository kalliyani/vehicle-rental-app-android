package com.example.carrent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {



    private Globalpreference globalpreference;
    String ip;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        globalpreference = new Globalpreference(this);
        ip = globalpreference.getIP();

    new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent =new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);

            }
        },3000);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();
    }
}