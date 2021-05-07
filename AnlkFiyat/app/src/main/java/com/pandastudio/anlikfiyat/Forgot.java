package com.pandastudio.anlikfiyat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Forgot extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText emailText;
    LinearLayout send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot);
        mAuth = FirebaseAuth.getInstance();

        setIdentify();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }
    public void setIdentify(){
        emailText = (EditText) findViewById(R.id.email);

        send = (LinearLayout) findViewById(R.id.send);

    }
    public void validate(){

        String email = emailText.getText().toString();

        if (email.isEmpty()){
            Toast.makeText( Forgot.this, "Email giriniz",
                    Toast.LENGTH_SHORT).show();
        }else{
            setForgot(email);
        }
    }
    public void setForgot(String email){

        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText( Forgot.this, "Email gönderildi",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText( Forgot.this, "Email gönderilemedi",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });





    }
}