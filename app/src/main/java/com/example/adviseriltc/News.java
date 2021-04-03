package com.example.adviseriltc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class News extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Game> states = new ArrayList<Game>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setInitialData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        // создаем адаптер
        GameAdapter adapter = new GameAdapter(this, states);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

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

    private void setInitialData(){

        states.add(new Game ("DOOM Eternal", "Single-player Online PvP Achievements", "2000 RUB",R.drawable.doom));
        states.add(new Game ("Minecraft", "Single-player Online", "500 RUB",R.drawable.minecraft));
        states.add(new Game ("Dota 2", "Online Achievements", "Free-to-play", R.drawable.dota2));
        states.add(new Game ("GRID 2", "Single-player", "1000 RUB", R.drawable.grid2));
        states.add(new Game ("TESO", "Online PvP Achievements", "4000 RUB", R.drawable.teso));
    }
}