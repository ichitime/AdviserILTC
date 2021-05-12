package com.example.adviseriltc;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Calendar extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;

    private DatabaseReference myRefCalendar;

    private ArrayList<NewGames_Calendar> gamesCalendarList;
    private NewGames_Adapter recyclerCalendarAdapter;
    private Context mContextCalendar;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

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

        recyclerView = findViewById(R.id.rv_calendar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        myRefCalendar= FirebaseDatabase.getInstance().getReference();

        gamesCalendarList= new ArrayList<>();

        GetDataFromFirebase();
    }

    private void GetDataFromFirebase() {
        Query query_calendar = myRefCalendar.child("Calendar");

        query_calendar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    NewGames_Calendar games_calendar = new NewGames_Calendar();

                    games_calendar.setImg(snapshot.child("IMG").getValue().toString());
                    games_calendar.setTitle(snapshot.child("TITLE").getValue().toString());
                    games_calendar.setRelease_date(snapshot.child("DATE").getValue().toString());
                    gamesCalendarList.add(games_calendar);
                }

                recyclerCalendarAdapter = new NewGames_Adapter(getApplicationContext(), gamesCalendarList);
                recyclerView.setAdapter(recyclerCalendarAdapter);
                recyclerCalendarAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClearAll(){
        if(gamesCalendarList!=null){
            gamesCalendarList.clear();

            if(recyclerCalendarAdapter!=null)
                recyclerCalendarAdapter.notifyDataSetChanged();
        }

        gamesCalendarList= new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView, textView1;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.game_img_calendar_new_id);
            textView=itemView.findViewById(R.id.game_title_calendar_new_id);
            textView1=itemView.findViewById(R.id.game_release_date_calendar_new_id);
        }

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
