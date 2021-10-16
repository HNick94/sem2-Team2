package com.example.team_2_tdp_mt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mFbAuth;           //Firebase Authentication
    private DatabaseReference myRef;        //Realtime database
    private EditText mEmail, mPwd, mFName, mLName;
    private Button btn_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFbAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("USER_nick");
        mEmail = findViewById(R.id.et_email);
        mPwd = findViewById(R.id.et_password);
        mFName = findViewById(R.id.et_fName);
        mLName = findViewById(R.id.et_lName);
        btn_reg = findViewById(R.id.button_register);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start register process
                String strEmail = mEmail.getText().toString();
                String strPwd = mPwd.getText().toString();
                String strFName = mFName.getText().toString();
                String strLName = mLName.getText().toString();

                //Start Authentication
                mFbAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = mFbAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setmStaffID(firebaseUser.getUid());
                            account.setmEmail(firebaseUser.getEmail());
                            account.setmPwd(strPwd);
                            account.setmFName(strFName);
                            account.setmLName(strLName);

                            //insert user info to database
                            myRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Registration Unsuccessful...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}