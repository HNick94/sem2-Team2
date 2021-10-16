package com.example.team_2_tdp_mt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportActivity extends AppCompatActivity {

    private EditText xValue, yValue;
    private Button btn_insert;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
    GraphView graphView;
    LineGraphSeries series;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        yValue = (EditText) findViewById(R.id.y_value);
        btn_insert = (Button) findViewById(R.id.btn_insert);
        graphView = (GraphView) findViewById(R.id.graphView);

        series = new LineGraphSeries();
        graphView.addSeries(series);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("chartTable17102021");

        setListeners();

        graphView.getGridLabelRenderer().setNumHorizontalLabels(6);

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {

                if(isValueX){
                    return sdf.format(new Date((long)value));

                } else {
                    return super.formatLabel(value, isValueX);
                }

            }
        });



    }

    private void setListeners() {
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = reference.push().getKey();
                long x = new Date().getTime();
                int y = Integer.parseInt(yValue.getText().toString());

                ReportValue reportValue = new ReportValue(x,y);

                reference.child(id).setValue(reportValue);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataPoint[] dp = new DataPoint[(int) snapshot.getChildrenCount()];
                int index = 0;

                for (DataSnapshot myDataSnapshot : snapshot.getChildren()){
                    ReportValue reportValue = myDataSnapshot.getValue(ReportValue.class);

                    dp[index] = new DataPoint(reportValue.getxValue(),reportValue.getyValue());
                    index++;
                }

                series.resetData(dp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}