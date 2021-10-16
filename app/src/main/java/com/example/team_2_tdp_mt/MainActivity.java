package com.example.team_2_tdp_mt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFbAuth;           //Firebase Authentication
    private DatabaseReference myRef;        //Realtime database
    Button sendButton, button_signIn;
    private EditText mEmail, mPwd, locationCode;
    public static final String STATION_NAME = "stationName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFbAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("USER_nick");
        mEmail = findViewById(R.id.et_email);
        mPwd = findViewById(R.id.et_password);


        //Log in Button
        sendButton = findViewById(R.id.button3);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Request log in
                String strEmail = mEmail.getText().toString();
                String strPwd = mPwd.getText().toString();

                mFbAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Log in Success
                            Intent intent = new Intent(MainActivity.this, StationRoute.class);
                            startActivity(intent);
                            //finish();       //Destroy current Activity.

                        }   else {
                            Toast.makeText(MainActivity.this, "Log in Unsuccessful..!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //Sign in Button
        button_signIn = findViewById(R.id.button_signIn);
        button_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Register page
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}










