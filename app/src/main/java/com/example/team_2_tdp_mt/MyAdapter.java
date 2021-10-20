package com.example.team_2_tdp_mt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ServerValue;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<Model> mList;
    Context context;


    public MyAdapter(Context context, ArrayList<Model> mList){
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mList.get(position);
        holder.busNumber.setText(model.getbusNumber());
        holder.passenger.setText(String.valueOf(model.getPassengers()));
        holder.goingTo.setText(model.getGoingTo());
        Date d = new Date(model.getTimestamp());
        SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
        holder.myTimeStamp.setText(sdf.format(d));



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView busNumber,passenger,goingTo,myTimeStamp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            busNumber = itemView.findViewById(R.id.bus_no);
            passenger = itemView.findViewById(R.id.passenger_info);
            goingTo = itemView.findViewById(R.id.going_to);
            myTimeStamp = itemView.findViewById(R.id.departed);







        }
    }
}
