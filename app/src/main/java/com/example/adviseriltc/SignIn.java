package com.example.adviseriltc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText editEmail, editPassword;

    private EditText signInEmail;
    private EditText signInPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        mAuth = FirebaseAuth.getInstance();



        //signed in
        //signed out
        //firebase.auth().setPersistence(firebase.auth.Auth.Persistence.LOCAL)
        FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
                if (user != null) {

                } else {
                    //signed out
                }
            }

        };
        signInEmail = (EditText) findViewById(R.id.email_sign_in);
        signInPassword = (EditText) findViewById(R.id.pswd_sign_in);

        Button signInBtn = (Button) findViewById(R.id.sign_in);
        signInBtn.setOnClickListener(this);
        ImageButton signInBack = (ImageButton) findViewById(R.id.sign_in_back);
        signInBack.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in:
                userLogin();
                break;
            case R.id.sign_in_back:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }


    private void userLogin() {
        String email = signInEmail.getText().toString().trim();
        String password = signInPassword.getText().toString().trim();

        if (email.isEmpty()) {
            signInEmail.setError("Заполните поле!");
            signInEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signInEmail.setError("Введите корректную почту!");
            signInEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            signInPassword.setError("Заполните поле!");
            signInPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(SignIn.this, News.class));
                } else {
                    Toast.makeText(SignIn.this, "Такого пользователя не существует!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}