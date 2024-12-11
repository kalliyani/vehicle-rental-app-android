package com.example.carrent;

import android.content.Context;
import android.content.SharedPreferences;

public class Globalpreference {


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private Context context;
    public Globalpreference(Context context){

        sharedPreferences =context.getSharedPreferences("sample",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();




    }

    public void saveip(String ipaddress){


        editor.putString("ip", ipaddress);
        editor.apply();

    }
    public String getIP(){

        return sharedPreferences.getString("ip","");

    }

    public void saveId(String userid) {
        editor.putString("id", userid);
        editor.apply();
    }

    public String getID(){

        return sharedPreferences.getString("id","");
    }

    public void saveName(String Name){
        editor.putString("name", Name);
        editor.apply();
    }

    public String getName(){

        return sharedPreferences.getString("name","");
    }









}
