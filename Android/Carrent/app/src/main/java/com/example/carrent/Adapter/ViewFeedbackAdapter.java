package com.example.carrent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrent.Globalpreference;
import com.example.carrent.ModelClass.Vehiclebooking_Modelclass;
import com.example.carrent.ModelClass.ViewFeedbackModelClass;
import com.example.carrent.R;

import java.util.ArrayList;

public class ViewFeedbackAdapter extends RecyclerView.Adapter<ViewFeedbackAdapter.MyViewHolder>{


    ArrayList<ViewFeedbackModelClass> list;
    Context context;
    String ip;

    Globalpreference globalpreference;

    public ViewFeedbackAdapter(ArrayList<ViewFeedbackModelClass> list, Context context){
        this.list = list;
        this.context = context;

        globalpreference = new Globalpreference(context);
        ip = globalpreference.getIP();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_viewfeedback, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewFeedbackAdapter.MyViewHolder holder, int position) {

        ViewFeedbackModelClass feedbackList = list.get(position);
        holder.usernameTV.setText(feedbackList.getName());
        holder.feedbackTV.setText(feedbackList.getFeedback());

        String iconText = feedbackList.getName();
        char first = iconText.charAt(0);
        String firstLetter = String.valueOf(first);

        //setting the first letter of user name as icon
        holder.userIconTV.setText(firstLetter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView userIconTV;
        TextView usernameTV;
        TextView feedbackTV;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            userIconTV = itemView.findViewById(R.id.feedBackIconTextView);
            usernameTV = itemView.findViewById(R.id.feedbackUserNameTextView);
            feedbackTV = itemView.findViewById(R.id.feedbackTV);


        }
    }
}
