package com.example.adviseriltc;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Random_game extends AppCompatActivity implements View.OnClickListener{

Button random_btn, link_btn;
TextView title;
ImageView img;
DatabaseReference myRef;
ArrayList<Random_game_texture> gamesList;
Random randomGenerator;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_game);

        ImageButton Home = (ImageButton) findViewById(R.id.home);
        Home.setOnClickListener(this);
        ImageButton Lupa = (ImageButton) findViewById(R.id.lupa);
        Lupa.setOnClickListener(this);
        ImageButton Calendar = (ImageButton) findViewById(R.id.calendar);
        Calendar.setOnClickListener(this);
        ImageButton Kubik = (ImageButton) findViewById(R.id.kubik);
        Kubik.setOnClickListener(this);
        ImageButton Profile = (ImageButton) findViewById(R.id.profile);
        Profile.setOnClickListener(this);

        random_btn=findViewById(R.id.random_game);
        link_btn=findViewById(R.id.link_page);

        title=findViewById(R.id.name_random);
        img=findViewById(R.id.img_random);

        myRef = FirebaseDatabase.getInstance().getReference();
        gamesList=new ArrayList<>();

        readNodes();

        random_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readNodes();

            }
        });

    }

    private  void readNodes(){

        int range = 371;
        int random = (int) (Math.random()*range);
        String node = String.valueOf(random);

        DatabaseReference ref = myRef.child("Parsing2").child(node);

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String title1 = dataSnapshot.child("НАЗВАНИЕ").getValue().toString();
                    String img1 = dataSnapshot.child("ИЗОБРАЖЕНИЕ 2").getValue().toString();

                    Random_game_texture randomObject = new Random_game_texture(title, img);

                    title.setText(title1);
                    //Picasso.get().load(title1).into((Target) title);
                    Uri my = Uri.parse(img1);
                    img.setImageURI(my);
                    Picasso.get().load(img1).into(img);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home:
                startActivity(new Intent(this, News.class));
                break;
            case R.id.lupa:
                startActivity(new Intent(this, Games.class));
                break;
            case R.id.calendar:
                startActivity(new Intent(this, Calendar.class));
                break;
            case R.id.kubik:
                startActivity(new Intent(this, Random_game.class));
                break;
            case R.id.profile:
                startActivity(new Intent(this, Profile.class));
                break;
        }
    }
}
