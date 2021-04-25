package com.example.adviseriltc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Games extends AppCompatActivity implements View.OnClickListener{


    RecyclerView recyclerView;

    private DatabaseReference myRef;

    private ArrayList<Game> gamesList;
    private RecyclerAdapter recyclerAdapter;
    private Context mContext;
   // private CardView click_card;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
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

        recyclerView = findViewById(R.id.recyclerview_id);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);

        myRef=FirebaseDatabase.getInstance().getReference();

        gamesList= new ArrayList<>();


        //ClearAll();


        GetDataFromFirebase();

//        click_card=(CardView)findViewById(R.id.cv_id);
//        click_card.setOnClickListener(this);
    }

    private void GetDataFromFirebase(){

        Query query_adventures = myRef.child("Parsing/Adventures");
        Query query_indi= myRef.child("Parsing/Indi");
        Query query_casuals= myRef.child("Parsing/Casuals");
        Query query_freegames= myRef.child("Parsing/Freegames");
        Query query_mmos= myRef.child("Parsing/Mmos");
        Query query_races= myRef.child("Parsing/Races");
        Query query_RP = myRef.child("Parsing/RP");
        Query query_simulator = myRef.child("Parsing/Simulator");

        query_adventures.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Game games = new Game();

                    games.setImage(snapshot.child("IMG").getValue().toString());
                    games.setTitle(snapshot.child("TITLE").getValue().toString());
                    games.setTag(snapshot.child("TAG").getValue().toString());
                    games.setPrice(snapshot.child("PRICE").getValue().toString());
                    gamesList.add(games);
                }

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), gamesList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        query_indi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Game games = new Game();

                    games.setImage(snapshot.child("IMG").getValue().toString());
                    games.setTitle(snapshot.child("TITLE").getValue().toString());
                    games.setTag(snapshot.child("TAG").getValue().toString());
                    games.setPrice(snapshot.child("PRICE").getValue().toString());
                    gamesList.add(games);
                }

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), gamesList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query_casuals.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Game games = new Game();

                    games.setImage(snapshot.child("IMG").getValue().toString());
                    games.setTitle(snapshot.child("TITLE").getValue().toString());
                    games.setTag(snapshot.child("TAG").getValue().toString());
                    games.setPrice(snapshot.child("PRICE").getValue().toString());
                    gamesList.add(games);
                }

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), gamesList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query_freegames.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Game games = new Game();

                    games.setImage(snapshot.child("IMG").getValue().toString());
                    games.setTitle(snapshot.child("TITLE").getValue().toString());
                    games.setTag(snapshot.child("TAG").getValue().toString());
                    games.setPrice(snapshot.child("PRICE").getValue().toString());
                    gamesList.add(games);
                }

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), gamesList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query_mmos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Game games = new Game();

                    games.setImage(snapshot.child("IMG").getValue().toString());
                    games.setTitle(snapshot.child("TITLE").getValue().toString());
                    games.setTag(snapshot.child("TAG").getValue().toString());
                    games.setPrice(snapshot.child("PRICE").getValue().toString());
                    gamesList.add(games);
                }

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), gamesList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query_races.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Game games = new Game();

                    games.setImage(snapshot.child("IMG").getValue().toString());
                    games.setTitle(snapshot.child("TITLE").getValue().toString());
                    games.setTag(snapshot.child("TAG").getValue().toString());
                    games.setPrice(snapshot.child("PRICE").getValue().toString());
                    gamesList.add(games);
                }

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), gamesList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        query_RP.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Game games = new Game();

                    games.setImage(snapshot.child("IMG").getValue().toString());
                    games.setTitle(snapshot.child("TITLE").getValue().toString());
                    games.setTag(snapshot.child("TAG").getValue().toString());
                    games.setPrice(snapshot.child("PRICE").getValue().toString());
                    gamesList.add(games);
                }

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), gamesList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        query_simulator.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Game games = new Game();

                    games.setImage(snapshot.child("IMG").getValue().toString());
                    games.setTitle(snapshot.child("TITLE").getValue().toString());
                    games.setTag(snapshot.child("TAG").getValue().toString());
                    games.setPrice(snapshot.child("PRICE").getValue().toString());
                    gamesList.add(games);
                }

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), gamesList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClearAll(){
        if(gamesList!=null){
            gamesList.clear();

            if(recyclerAdapter!=null)
                recyclerAdapter.notifyDataSetChanged();
        }

        gamesList= new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView, textView1, textView2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.game_img_id);
            textView=itemView.findViewById(R.id.game_title_id);
            textView1=itemView.findViewById(R.id.game_tags_id);
            textView2=itemView.findViewById(R.id.game_price_id);
        }

        }

    @Override
    public void onClick(View v) {
        Intent i;
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