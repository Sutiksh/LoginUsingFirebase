package com.inc.sutiksh.loginwithfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText emailT;
    private EditText passT;
    private Button logOn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailT = (EditText) findViewById(R.id.emailT);
        passT = (EditText) findViewById(R.id.passT);
        logOn = (Button) findViewById(R.id.logon);
        mAuth = FirebaseAuth.getInstance();

        logOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailT.getText().toString();
                String pass = passT.getText().toString();

                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this,AccountActivity.class));
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"Sign In Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}
