package com.example.carrent.ModelClass;

public class Vehicledetails_Modelclass {

    String id;
    String name;
    String model;
    String location;
    String image;
    String rate;
    String status;


    public Vehicledetails_Modelclass(String id, String name, String model,String location, String image, String rate, String status){
        this.id = id;
        this.name = name;
        this.model = model;
        this.location = location;
        this.image = image;
        this.rate= rate;
      //  this.status = status;

    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getModel(){return model;}

    public  String getLocation(){return location;}

    public String getImage(){
        return image;
    }


    public String getRate(){
        return rate;
    }

 //   public String getStatus(){
      //  return status;
    }











