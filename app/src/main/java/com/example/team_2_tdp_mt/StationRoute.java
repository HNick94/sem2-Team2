package com.example.team_2_tdp_mt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StationRoute extends AppCompatActivity {
    Spinner spinner1,spinner2;

    ArrayList<String> array_busLines;
    ArrayAdapter<String> arrayAdapter_busLines;

    ArrayList<String> arrayList_lilyDale, arrayList_belGrave;
    ArrayAdapter<String> arrayAdapter_station;

    Button btn;
    FirebaseDatabase database;
    DatabaseReference reference;
    StationAndRoute stationAndRoute;
    int maxId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_route);
        spinner1 = (Spinner) findViewById(R.id.route_lines);
        spinner2 = (Spinner) findViewById(R.id.station) ;

        array_busLines = new ArrayList<>();
        array_busLines.add("Lilydale");
        array_busLines.add("Belgrave");


      arrayAdapter_busLines = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_busLines);
      spinner1.setAdapter(arrayAdapter_busLines);

      //spinner selected based on the bus Line
        arrayList_lilyDale = new ArrayList<>();
        arrayList_lilyDale.add("Mooroolbark");
        arrayList_lilyDale.add("Croydon");
        arrayList_lilyDale.add("Ringwood");
        arrayList_lilyDale.add("Mitcham");

        arrayList_belGrave = new ArrayList<>();
        arrayList_belGrave.add("Tecoma");
        arrayList_belGrave.add("Upwey");
        arrayList_belGrave.add("Boronia");
        arrayList_belGrave.add("Bayswater");
        btn = findViewById(R.id.station_button);
        stationAndRoute = new StationAndRoute();
        reference = database.getInstance().getReference().child("Route");
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
               if(position == 0){
                   arrayAdapter_station = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_lilyDale);
               }
               if(position == 1){
                   arrayAdapter_station = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_belGrave);
               }

               spinner2.setAdapter(arrayAdapter_station);

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxId = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stationAndRoute.setLine(spinner1.getSelectedItem().toString());
                stationAndRoute.setStation(spinner2.getSelectedItem().toString());
                reference.child(String.valueOf(maxId +1)).setValue(stationAndRoute);
                int index = spinner1.getSelectedItemPosition();
                Intent intent = new Intent(StationRoute.this, DataInputScreen.class);
                intent.putExtra("pos",index);
                startActivity(intent);
            }
        });


    }
}