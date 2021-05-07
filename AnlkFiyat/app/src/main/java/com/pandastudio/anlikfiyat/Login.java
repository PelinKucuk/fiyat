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

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailText;
    EditText passwordText;
    LinearLayout login,register;
    TextView forgot_passord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();

        setIdentify();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
        forgot_passord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Forgot.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
    public void setIdentify(){
        emailText = (EditText) findViewById(R.id.email);
        passwordText = (EditText) findViewById(R.id.password);
        login = (LinearLayout) findViewById(R.id.login);
        register = (LinearLayout) findViewById(R.id.register);
        forgot_passord = (TextView) findViewById(R.id.forgot_passord);

    }
    public void validate(){

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty()){
            Toast.makeText( Login.this, "Email giriniz",
                    Toast.LENGTH_SHORT).show();
        }else if (password.isEmpty()){
            Toast.makeText( Login.this, "Şifre giriniz",
                    Toast.LENGTH_SHORT).show();
        }else if (password.length() < 6){
            Toast.makeText( Login.this, "Şifre en az 6 karakter girmelisiniz",
                    Toast.LENGTH_SHORT).show();
        }else{
            setLogin(email,password);
        }
    }
    public void setLogin(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            Intent intent = new Intent(Login.this, Dashboard.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText( Login.this, "Giriş Yapılamadı",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}