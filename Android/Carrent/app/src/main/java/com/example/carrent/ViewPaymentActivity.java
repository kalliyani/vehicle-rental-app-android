package com.example.carrent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.carrent.ModelClass.Vehicledetails_Modelclass;

import java.util.ArrayList;

public class ViewPaymentActivity extends AppCompatActivity {

    RecyclerView PaymentRV;

    private Globalpreference globalpreference;
    String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_payment);

        globalpreference = new Globalpreference(this);
        ip = globalpreference.getIP();

        PaymentRV = findViewById(R.id.VpaymentRV);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        PaymentRV.setLayoutManager(layoutManager);
    }
}