package com.example.adviseriltc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class News extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;

    private DatabaseReference myRefNews;

    private ArrayList<New> NewsList;
    private News_Adapter recyclerNewsAdapter;
    private Context mContextCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

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


        recyclerView = findViewById(R.id.news_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setHasFixedSize(true);


        myRefNews= FirebaseDatabase.getInstance().getReference();

        NewsList= new ArrayList<>();

        GetDataFromFirebase();



    }
    private void GetDataFromFirebase() {
        Query query_news = myRefNews.child("News");

        query_news.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    New news_list = new New();

                    news_list.setImg(snapshot.child("ИЗОБРАЖЕНИЕ").getValue().toString());
                    news_list.setTitle(snapshot.child("ЗАГОЛОВОК").getValue().toString());
                    news_list.setDescription(snapshot.child("ОПИСАНИЕ").getValue().toString());
                    news_list.setLink(snapshot.child("ССЫЛКА").getValue().toString());
                    NewsList.add(news_list);
                }

                recyclerNewsAdapter = new News_Adapter(getApplicationContext(), NewsList);
                recyclerView.setAdapter(recyclerNewsAdapter);
                recyclerNewsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClearAll(){
        if(NewsList!=null){
            NewsList.clear();

            if(recyclerNewsAdapter!=null)
                recyclerNewsAdapter.notifyDataSetChanged();
        }

        NewsList= new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView, textView1;
        CardView cardView;
        String link;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.img_news);
            textView=itemView.findViewById(R.id.title_news);
            textView1=itemView.findViewById(R.id.dsc_news);
            cardView=itemView.findViewById(R.id.cv_news);
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    gotoUrl(link);
//                }
//
//                private void gotoUrl(String link) {
//                    Uri uri=Uri.parse(link);
//                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
//                }
//            });
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