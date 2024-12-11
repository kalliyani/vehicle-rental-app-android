package com.example.carrent.ModelClass;

public class ViewPayment_Modelclass {


    String amount;
    String date;

    public ViewPayment_Modelclass(String amount,String date){

        this.amount = amount;
        this.date = date;

    }

    public String getAmount(){return amount;}
    public String getDate(){return date;}



}
