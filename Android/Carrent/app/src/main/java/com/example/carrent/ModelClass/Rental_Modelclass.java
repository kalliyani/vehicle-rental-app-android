package com.example.carrent.ModelClass;

public class Rental_Modelclass {

    String id;
    String carname;
    String carmodel;
    String booking_date;
    String noofdays;
    String status;
    String amount;
    String image;

    public Rental_Modelclass(String id,String carname,String carmodel,String booking_date,String noofdays,String amount,String status,String image){
        this.id = id;
        this.carname = carname;
        this.carmodel = carmodel;
        this.booking_date = booking_date;
        this.noofdays = noofdays;
        this.amount = amount;
        this.status = status;
        this.image = image;
    }

    public String getId(){
        return id;
    }
    public String getCarname(){
        return carname;
    }
    public String getCarmodel(){
        return carmodel;
    }
    public String getBooking_date(){
        return booking_date;
    }
    public  String getAmount(){return amount;}
    public String getStatus(){
        return status;
    }
    public String getNoofdays(){
        return noofdays;
    }
    public String getImage(){return image;}

}
