package com.example.team_2_tdp_mt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Model> mList;
    private Context context;

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

    }

    @Override
    public int getItemCount() {
        return (mList != null ? mList.size() : 0);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView busNumber,passenger;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            busNumber = itemView.findViewById(R.id.busNumber);

            passenger = itemView.findViewById(R.id.passenger_info);


        }
    }
}
