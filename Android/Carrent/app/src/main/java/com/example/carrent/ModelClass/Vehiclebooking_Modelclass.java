package com.example.carrent.ModelClass;

public class Vehiclebooking_Modelclass {

    String id;
    String carname;
    String carmodel;
    String booking_date;
    String noofdays;
    String status;
    String amount;
    String image;

    public Vehiclebooking_Modelclass(String id,String carname,String carmodel,String booking_date,String noofdays,String status,String amount,String image){
        this.id = id;
        this.carname = carname;
        this.carmodel = carmodel;
        this.booking_date = booking_date;
        this.noofdays = noofdays;
        this.status = status;
        this.amount = amount;
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
    public String getStatus(){
        return status;
    }
    public  String getAmount(){return amount;}
    public String getNoofdays(){
        return noofdays;
    }

    public String getImage(){return image;}

}
