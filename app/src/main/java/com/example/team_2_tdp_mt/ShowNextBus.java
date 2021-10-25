package com.example.team_2_tdp_mt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.SearchView.OnQueryTextListener;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowNextBus extends AppCompatActivity {
    private RecyclerView recyclerView;
    SearchView mySearchView;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private MyAdapter adapter;
    private ArrayList<Model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_next_bus);
        recyclerView = findViewById(R.id.recycle_view);
        mySearchView = findViewById(R.id.mySearchBar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("main");
        adapter = new MyAdapter(this,list);

    }

    protected void onStart(){
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Model model = dataSnapshot.getValue(Model.class);
                    list.add(model);
                }

                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);


            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(mySearchView != null){
            mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }
    private void search(String s){
        ArrayList<Model> newList = new ArrayList<>();
        for(Model m : list){
            if(m.getGoingTo().toLowerCase().contains(s.toLowerCase())){
                newList.add(m);
            }
        }
        MyAdapter adapterNew = new MyAdapter(this, newList);
        recyclerView.setAdapter(adapterNew);
    }

}