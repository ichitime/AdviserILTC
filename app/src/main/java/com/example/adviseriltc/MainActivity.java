package com.example.adviseriltc;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button sign_up, sign_in, login_guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        sign_up = (Button) findViewById(R.id.sign_up_btn);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        sign_in = (Button) findViewById(R.id.sign_in_btn);
        sign_in.setOnClickListener(this);
        sign_up = (Button) findViewById(R.id.sign_up_btn);
        sign_up.setOnClickListener(this);
        sign_up = (Button) findViewById(R.id.login_guest_btn);
        sign_up.setOnClickListener(this);

    }

    FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
//                if (user != null) {
//
//                } else {
//                    //signed out
//                }
            if (user != null) {  //User is signed in
                Intent i = new Intent(MainActivity.this, News.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            } else {
                // User is signed out
                //Log.d(TAG, "onAuthStateChanged:signed_out");
            }
        }

    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_btn:
                startActivity(new Intent(this, SignIn.class));
                break;
            case R.id.sign_up_btn:
                startActivity(new Intent(this, SignUp.class));
                break;
            case R.id.login_guest_btn:
                startActivity(new Intent(this, News.class));
                break;
        }
    }
}