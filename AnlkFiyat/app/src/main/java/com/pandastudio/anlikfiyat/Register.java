package com.pandastudio.anlikfiyat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailText, usernameText;
    EditText passwordText,passwordVerifyText;
    LinearLayout register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth = FirebaseAuth.getInstance();

        setIdentify();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }
    public void setIdentify(){
        emailText = (EditText) findViewById(R.id.email);
        passwordText = (EditText) findViewById(R.id.password);
        passwordVerifyText = (EditText)findViewById(R.id.password_verify);
        register = (LinearLayout) findViewById(R.id.register);
    }
    public void validate(){

        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        String passwordVerify = passwordVerifyText.getText().toString().trim();

        if (email.isEmpty()){
            Toast.makeText( Register.this, "Email giriniz",
                    Toast.LENGTH_SHORT).show();
        }else if (password.isEmpty()){
            Toast.makeText( Register.this, "Şifre giriniz",
                    Toast.LENGTH_SHORT).show();
        }else if (password.length() < 6){
            Toast.makeText( Register.this, "Şifre en az 6 karakter girmelisiniz",
                    Toast.LENGTH_SHORT).show();
        }else if (passwordVerify.isEmpty()){
            Toast.makeText( Register.this, "Şifrenizi tekrar giriniz",
                    Toast.LENGTH_SHORT).show();
        }else if (passwordVerify.length() < 6){
            Toast.makeText( Register.this, "Tekrar girdiğiniz şifre en az 6 karakter girmelisiniz",
                    Toast.LENGTH_SHORT).show();
        }else if (password == passwordVerify){
            Toast.makeText( Register.this, "Şifreniz aynı olmalıdır",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            setRegister( email,password,passwordVerify);
        }
    }
    public void setRegister( String email,String password, String passwordVerify){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            Toast.makeText( Register.this, "Üye işlemleri tamamlandı",
                              Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText( Register.this, "Kayıt Yapılamadı",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}