package com.example.carrent;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BookingActivity extends AppCompatActivity {

     String carid;

     EditText licenseET;
     EditText numberofdaysET;
     ImageView uploadIV;
     Button continuebtn;

    private Globalpreference globalpreference;
    String ip;
    String userid;
    String amount;
    String encodeImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        globalpreference = new Globalpreference(this);
        ip = globalpreference.getIP();
        userid = globalpreference.getID();


        carid = getIntent().getStringExtra("carid");
        amount = getIntent().getStringExtra("amount");

        Toast.makeText(this, ""+amount, Toast.LENGTH_SHORT).show();

        licenseET = findViewById(R.id.LicenseNumberET);
        numberofdaysET = findViewById(R.id.NumberofdaysET);
        uploadIV = findViewById(R.id.UploadIV);
        continuebtn = findViewById(R.id.continuebutton);

        uploadIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent();
                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),100);

            }
        });

        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getuserdetails();
            }
        });



    }

    private void getuserdetails() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://"+ ip +"/car_rent/api/Booking.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")){
                    Toast.makeText(BookingActivity.this, "Booked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookingActivity.this,HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(BookingActivity.this,""+response,Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BookingActivity.this,""+error,Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_id",userid);
                params.put("car_id",carid);
                params.put("license_no",licenseET.getText().toString());
                params.put("no_of_days",numberofdaysET.getText().toString());
                params.put("image",encodeImage);
                params.put("amount",amount);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(BookingActivity.this);
        requestQueue.add(stringRequest);
    }






    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (resultCode == RESULT_OK){
            if (requestCode == 100){
                Uri filepath = data.getData();

                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte [] imageBytes = baos.toByteArray();
                    encodeImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    uploadIV.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
}}