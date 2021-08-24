package com.example.memeshareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity
{
    EditText email,password;
    Button loginbtn;
    FirebaseAuth Fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        email = findViewById(R.id.useremail);
        password = findViewById(R.id.userpasss);
        loginbtn = findViewById(R.id.loginuser);
        Fauth = FirebaseAuth.getInstance();
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               if(email.getText().toString().isEmpty())
               {
                  email.setError("Email Missing");
                  return;
               }
               if(password.getText().toString().isEmpty())
               {
                  password.setError("Password Missing");
                  return;
               }
               Fauth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>()
               {
                   @Override
                   public void onSuccess(AuthResult authResult)
                   {
                    Intent intent = new Intent(Login_Activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e)
                   {
                    Toast.makeText(Login_Activity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                   }
               });
            }
        });
    }
        public void OpenRegister (View view)
        {
            Intent in = new Intent(Login_Activity.this, Register.class);
            startActivity(in);
        }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
          Intent intent = new Intent(Login_Activity.this,MainActivity.class);
          startActivity(intent);
          finish();
        }
    }


}