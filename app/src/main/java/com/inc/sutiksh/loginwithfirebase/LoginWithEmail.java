package com.inc.sutiksh.loginwithfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginWithEmail extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private Button mSignUp;
    private FirebaseAuth mAuth;
    private TextView loginText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_login);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mSignUp = (Button) findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        loginText = (TextView)findViewById(R.id.log_text);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginWithEmail.this,LoginActivity.class));
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    registerUser();
            }
        });


    }
    private void registerUser(){
        String email = mEmail.getText().toString();
        String pass = mPassword.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
            Toast.makeText(LoginWithEmail.this,"One Of The Field Is Missing!!",Toast.LENGTH_LONG).show();
        }

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginWithEmail.this,"Successfully Registered!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginWithEmail.this,AccountActivity.class));
                }
                else{
                    Toast.makeText(LoginWithEmail.this,"Registration Failed!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
