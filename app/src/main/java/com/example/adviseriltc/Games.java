package com.example.adviseriltc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import java.util.Collections;
import java.util.List;


public class Games extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;

    private DatabaseReference myRef;

    private ArrayList<Game> gamesList;
    private RecyclerAdapter recyclerAdapter;
    private Context mContext;

    SharedPreferences preferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        getMenuInflater().inflate(R.menu.menu, menu);
//
//        MenuItem item = menu.findItem(R.id.action_search);

        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        MenuItem item=menu.findItem(R.id.action_search);

        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                recyclerAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id= item.getItemId();

        if (id==R.id.action_sort){
            sortDailog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortDailog() {
        String[] options ={"Ascending", "Descending"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort by:");
        builder.setIcon(R.drawable.sort_img);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which==0){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort", "ascending");
                    editor.apply();
                    recreate();
                }

                if(which==1){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort", "descending");
                    editor.apply();
                    recreate();
                }
            }
        });

        builder.create().show();
    }

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

        preferences=this.getSharedPreferences("My_pref", MODE_PRIVATE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);


        myRef=FirebaseDatabase.getInstance().getReference();

        gamesList= new ArrayList<>();

        //ClearAll();

        GetDataFromFirebase();

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private void GetDataFromFirebase(){

        Query query_simulator = myRef.child("Parsing2");


        query_simulator.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Game games = new Game();

                    games.setImage(snapshot.child("ИЗОБРАЖЕНИЕ").getValue().toString());
                    games.setTitle(snapshot.child("НАЗВАНИЕ").getValue().toString());
                    games.setTag1(snapshot.child("ТЭГ1").getValue().toString());
                    games.setTag2(snapshot.child("ТЭГ2").getValue().toString());
                    games.setTag3(snapshot.child("ТЭГ3").getValue().toString());
                    games.setTag4(snapshot.child("ТЭГ4").getValue().toString());
                    games.setPrice(snapshot.child("ЦЕНА").getValue().toString());
                    games.setLink(snapshot.child("ССЫЛКА").getValue().toString());
                    games.setVideo(snapshot.child("ВИДЕО").getValue().toString());
                    games.setRelease_date(snapshot.child("ДАТА ВЫХОДА").getValue().toString());
                    games.setPublisher(snapshot.child("ИЗДАТЕЛЬ").getValue().toString());
                    games.setDescription(snapshot.child("ОПИСАНИЕ").getValue().toString());
                    games.setDeveloper(snapshot.child("РАЗРАБОТЧИК").getValue().toString());
                    games.setGenre(snapshot.child("ЖАНР").getValue().toString());
                    gamesList.add(games);

                }

                String mSortSetting = preferences.getString("Sort", "ascending");

                if(mSortSetting.equals("ascending")){
                    Collections.sort(gamesList, Game.BY_TITLE_ASCENDING);
                }
                else if(mSortSetting.equals("descending")){
                    Collections.sort(gamesList, Game.BY_TITLE_DESCENDING);
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