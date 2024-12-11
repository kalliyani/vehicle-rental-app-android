package com.example.carrent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.carrent.Adapter.BookingAdapter;
import com.example.carrent.Adapter.ViewFeedbackAdapter;
import com.example.carrent.ModelClass.Vehiclebooking_Modelclass;
import com.example.carrent.ModelClass.ViewFeedbackModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewFeedbackActivity extends AppCompatActivity {

    RecyclerView viewFeedbackRV;
    ArrayList<ViewFeedbackModelClass> list;
    private Globalpreference globalpreference;

    String ip,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);

        globalpreference = new Globalpreference(this);
        ip = globalpreference.getIP();
        id = globalpreference.getID();

        viewFeedbackRV = findViewById(R.id.ViewFeedbackRV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        viewFeedbackRV.setLayoutManager(layoutManager);

        viewfeedback();




    }

    private void viewfeedback() {


        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://"+ip+"/car_rent/api/viewFeedback.php?uid="+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("failed")) {



                    Toast.makeText(ViewFeedbackActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String name = object.getString("name");
                            String feedback = object.getString("feedback");


                            list.add(new ViewFeedbackModelClass(id,name,feedback));

                            ViewFeedbackAdapter adapter = new ViewFeedbackAdapter(list,ViewFeedbackActivity.this);
                            viewFeedbackRV.setAdapter(adapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewFeedbackActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(ViewFeedbackActivity.this);
        requestQueue.add(stringRequest);
    }
}