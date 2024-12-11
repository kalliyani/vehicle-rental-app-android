package com.example.carrent.ModelClass;

public class ViewFeedbackModelClass {

    String id;
    String name;
    String feedback;

    public ViewFeedbackModelClass(String id,String name,String feedback){
        this.id = id;
        this.name = name;
        this.feedback = feedback;
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getFeedback(){
        return feedback;
    }
}
