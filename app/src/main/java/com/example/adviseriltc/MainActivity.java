package com.example.adviseriltc;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button sign_up, sign_in;

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_btn:
                startActivity(new Intent(this, SignIn.class));
                break;
            case R.id.sign_up_btn:
                startActivity(new Intent(this, SignUp.class));
                break;
            case R.id.vk_auth:
            case R.id.facebook_auth:
                Toast.makeText(MainActivity.this, "Временно недоступно!", Toast.LENGTH_LONG).show();
                break;
        }
    }
}