package com.example.carrent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {


    private RadioButton cardRadioButton;
    private TextView paymentTVButton;
    EditText cardnoEditText;
    EditText  cvvEditText;
    LinearLayout cardDetailsLL;

    private static final String TAG = "PaymentActivity";

    String userid;
     String ip;
    String ptype;


    List<String> month = new ArrayList<>();
    List<String> year = new ArrayList<>();
    Spinner MMspin;
    Spinner YYspin;
    EditText amountET;

    String bookingid, amount;

    private ImageView backIV;
    private TextView appbarTitleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        bookingid = getIntent().getStringExtra("bid");
        amount = getIntent().getStringExtra("amount");

        Toast.makeText(this, ""+amount, Toast.LENGTH_SHORT).show();

        iniit();

        appbarTitleTV.setText("Payment");
        backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });


        month.add("MM");
        for (int i = 1 ; i <= 12 ; i++)
        {
            month.add(String.valueOf(i));
        }

        year.add("YY");
        for(int i = 21 ; i<=30 ; i++ ) {
            year.add(String.valueOf(i));
        }


        MMspin = (Spinner) findViewById(R.id.mmspinner);
        ArrayAdapter MM = new ArrayAdapter(this,android.R.layout.simple_spinner_item,month);
        MM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MMspin.setAdapter(MM);

        YYspin = (Spinner) findViewById(R.id.yyspinner);
        ArrayAdapter YY = new ArrayAdapter(this,android.R.layout.simple_spinner_item,year);
        YY.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        YYspin.setAdapter(YY);

        Globalpreference globalPreference =new Globalpreference(this);
        ip = globalPreference.getIP();
       userid = globalPreference.getID();


        amountET.setText("₹ "+amount);

        cardRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cardRadioButton.isChecked()) {
                    ptype = "Card Payment";
                    cardDetailsLL.setVisibility(View.VISIBLE);
                }


            }
        });

        paymentTVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!cardRadioButton.isChecked()) {
                    Toast.makeText(PaymentActivity.this, "Please select a payment method ", Toast.LENGTH_SHORT).show();
                } else if (cardRadioButton.isChecked() && cardnoEditText.getText().toString().equals("") ||
                        cardRadioButton.isChecked() && cvvEditText.getText().toString().equals("") ||
                        cardRadioButton.isChecked() && MMspin.getSelectedItem().toString().equals("MM") ||
                        cardRadioButton.isChecked() && YYspin.getSelectedItem().toString().equals("YY")) {
                    Toast.makeText(PaymentActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    paynow();

                }


            }
        });








    }

    private void paynow() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/car_rent/api/payment.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                if (response.equals("success")) {
                    Toast.makeText(PaymentActivity.this, "Payment Completed", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(PaymentActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(PaymentActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PaymentActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("uid",userid);
                params.put("bid",bookingid);
                params.put("amount",amount);
                params.put("cardnumber",cardnoEditText.getText().toString());
                return params;

        }


    };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    private void iniit() {

        cardRadioButton = findViewById(R.id.radioCard);
        paymentTVButton = findViewById(R.id.paymentTextViewButton);
        cardDetailsLL = findViewById(R.id.cardDetailsLinearLayout);
        cardnoEditText = findViewById(R.id.cardnoEditText);
        cvvEditText = findViewById(R.id.cvvEditText);
        amountET = findViewById(R.id.amountEditText);
        appbarTitleTV = findViewById(R.id.appBarTitle);
        backIV = findViewById(R.id.BackImageButton);


    }
}