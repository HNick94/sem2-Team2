package com.example.team_2_tdp_mt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DataInputScreen extends AppCompatActivity {
    EditText busNumber;
    EditText passengers;
    EditText offBoard;
    TextClock time;
    Button sendButton;
    Button showBusButton;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Spinner mySpinner;
    ArrayList<String> arrayList_lilyDale, arrayList_belGrave;
    ArrayAdapter<String> arrayAdapter_station;
    String currentTime = java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input_screen);
        mySpinner = findViewById(R.id.station_spinner) ;
        busNumber = findViewById(R.id.editTextTextPersonName7);
        passengers = findViewById(R.id.editTextTextPersonName8);
        offBoard = findViewById(R.id.editTextTextPersonName9);
        time =  findViewById(R.id.textClock);
        sendButton = findViewById(R.id.button2);
        showBusButton = findViewById(R.id.show_next_bus);

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

        int getIndex = getIntent().getIntExtra("pos",0);
        if(getIndex == 0){
            arrayAdapter_station = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList_lilyDale);
            arrayAdapter_station.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if(getIndex == 1){
            arrayAdapter_station = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList_belGrave);
            arrayAdapter_station.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        mySpinner.setAdapter(arrayAdapter_station);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        showBusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DataInputScreen.this,ShowNextBus.class));
            }
        });
       
        //retrieve user input and listen for onClick event listener
        sendButton.setOnClickListener(view -> {
            //get database instance and write to database
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("main");
            String busInfo = busNumber.getText().toString();
            String numberOfPass = passengers.getText().toString();
            String getOff = offBoard.getText().toString();

            DataScreen info = new DataScreen(busInfo,numberOfPass,getOff);
            myRef.child(busInfo).setValue(info);
            //clear fields after submitting
            busNumber.getText().clear();
            passengers.getText().clear();
            offBoard.getText().clear();

        });

    }
}