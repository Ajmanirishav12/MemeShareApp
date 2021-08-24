package com.example.memeshareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.fonts.FontFamily;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity
{
 EditText fullname,email,pass,reenter;
 Button btn;
 FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.mailid);
        pass = findViewById(R.id.password);
        reenter = findViewById(R.id.reenterpass);
        fAuth = FirebaseAuth.getInstance();
    }

    public void Click(View view) {
        String name = fullname.getText().toString();
        String emaill = email.getText().toString();
        String passwword = pass.getText().toString();
        String repas = reenter.getText().toString();
        if (name.isEmpty()) {
            fullname.setError("Enter Full Name");
            return;
        }
        if (emaill.isEmpty()) {
            email.setError("Please Enter Email");
            return;
        }
        if (passwword.isEmpty()) {
            pass.setError("Please Enter Password");
            return;
        }
        if (repas.isEmpty()) {
            reenter.setError("Please ReEnter Your Password");
            return;
        }
        if (!(passwword.equals(repas))) {
            reenter.setError("Password Doesn't Match");
            return;
        }
        fAuth.createUserWithEmailAndPassword(emaill,passwword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult)
            {
               Toast.makeText(Register.this,"Success",Toast.LENGTH_LONG).show();
               Intent intent = new Intent(Register.this,MainActivity.class);
               startActivity(intent);
               finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
             Toast.makeText(Register.this, e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        //Now all things are good//
    }

    public void Login(View view)
    {
     Intent intent = new Intent(Register.this,Login_Activity.class);
     startActivity(intent);
     finish();
    }
}