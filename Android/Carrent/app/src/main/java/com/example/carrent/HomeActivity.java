package com.example.carrent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    TextView nameTV;

    CardView viewvehicleCV;

    CardView viewbookingCV;
    CardView viewrentals;
    CardView feedbackCV;
    CardView viewFeedbackCV;
    ImageView profileIV;
    LinearLayout logLL;

    private Globalpreference globalpreference;
    String ip;
    String name;
    String cardid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        globalpreference = new Globalpreference(this);
        ip = globalpreference.getIP();
        name = globalpreference.getName();

        nameTV = findViewById(R.id.nameTextView);
       viewbookingCV = findViewById(R.id.ViewBookingCV);
       profileIV = findViewById(R.id.profileImageView);
       viewrentals = findViewById(R.id.ViewRentalCV);
       feedbackCV = findViewById(R.id.FeedbackCV);
       logLL = findViewById(R.id.logoutLL);
        viewvehicleCV = findViewById(R.id.ViewVehicleCV);
        viewFeedbackCV = findViewById(R.id.ViewfeedbackCV);

        nameTV.setText(name);

        viewvehicleCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ViewVehiclesActivity.class);
                startActivity(intent);
            }
        });

        viewbookingCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this,BookingDetailsActivity.class);
                startActivity(intent);

            }
        });
        profileIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(intent);

            }
        });
        viewrentals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,RentalActivity.class);
                startActivity(intent);
            }
        });

        feedbackCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this,FeedbackActivity.class);
                startActivity(intent);

            }
        });

        viewFeedbackCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this,ViewFeedbackActivity.class);
                startActivity(intent);

            }
        });

        logLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alert();


            }
        });
    }

    private void alert() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Logout");
        alertDialogBuilder.setMessage("You will be returned to the login screen");

        alertDialogBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(HomeActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);


            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}