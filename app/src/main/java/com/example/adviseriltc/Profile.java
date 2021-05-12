package com.example.adviseriltc;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    DatabaseReference myRef;
    TextView email;
    //ArrayList<Current_User> current_users;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth=FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference();
        //current_users=new ArrayList<>();

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
        Button Logout =(Button) findViewById(R.id.logout_btn);


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                signOutUser();
            }
        });

        email=findViewById(R.id.current_email);
        readEmail();
    }

    private void readEmail(){
//        DatabaseReference ref = myRef.child("Users");

        Query query =myRef.child("Users");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (mAuth.getCurrentUser()!=null){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email_DB = user.getEmail();

                //String email_DB = dataSnapshot.child("email").getValue().toString();
                //Current_User userObject = new Current_User(email);

                email.setText(email_DB);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void signOutUser() {
        Intent mainActivity = new Intent(Profile.this, MainActivity.class);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainActivity);
        finish();
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
