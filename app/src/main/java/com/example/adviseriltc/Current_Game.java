package com.example.adviseriltc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Current_Game extends AppCompatActivity implements View.OnClickListener{

    private TextView textView_title, textView_price, textView_link, textView_publisher,textView_developer,textView_release,textView_description,textView_genre;
    private VideoView videoView_promo;
    Button button_link;
    private FirebaseAuth mAuth;
    boolean flag = true;
    DatabaseReference myRef;
    ArrayList<fvdGame> fvdGamesList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_game);

        ImageButton signInBack = (ImageButton) findViewById(R.id.sign_in_back);
        signInBack.setOnClickListener(this);
        button_link=findViewById(R.id.links_game_id);


        mAuth = FirebaseAuth.getInstance();

        myRef=FirebaseDatabase.getInstance().getReference();

        textView_title=findViewById(R.id.game_title_id);
        textView_price=findViewById(R.id.game_price_id);
        textView_link=findViewById(R.id.links_game_id);
        textView_publisher=findViewById(R.id.publisher_name_id);
        textView_developer=findViewById(R.id.developer_name_id);
        textView_release=findViewById(R.id.release_date_name_id);
        textView_description=findViewById(R.id.game_description_id);
        textView_genre=findViewById(R.id.genre_name_id);
        videoView_promo=findViewById(R.id.banner);

        MediaController mediaController=new MediaController(this);
        videoView_promo.setMediaController(mediaController);
        mediaController.setAnchorView(videoView_promo);

        Intent intent=getIntent();
        String Title=intent.getExtras().getString("Title");
        String Price=intent.getExtras().getString("Price");
        String Link=intent.getExtras().getString("Link");
        String Publisher=intent.getExtras().getString("Publisher");
        String Developer=intent.getExtras().getString("Developer");
        String Release=intent.getExtras().getString("Release");
        String Description=intent.getExtras().getString("Description");
        String Genre=intent.getExtras().getString("Genre");
        String Video=intent.getExtras().getString("Video");


        textView_title.setText(Title);
        textView_price.setText(Price);
        //textView_link.setText(Link);
        textView_publisher.setText(Publisher);
        textView_developer.setText(Developer);
        textView_release.setText(Release);
        textView_description.setText(Description);
        textView_genre.setText(Genre);
        videoView_promo.setVideoPath(Video);

        button_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl(Link);
            }

            private void gotoUrl(String link) {
                Uri uri=Uri.parse(link);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });


        final ImageButton imageButton_like = (ImageButton) findViewById(R.id.like_button);
        imageButton_like.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

//                    ArrayList<Game> ggg = new ArrayList<>().add()
                    if (flag){
                        imageButton_like.setImageResource(R.drawable.like_form2);
                        Toast.makeText(com.example.adviseriltc.Current_Game.this, "Добавлено в избранное", Toast.LENGTH_SHORT).show();
                        flag = false;

                    }else{

                        imageButton_like.setImageResource(R.drawable.like_form1);
                        Toast.makeText(com.example.adviseriltc.Current_Game.this, "Удалено из избранного", Toast.LENGTH_SHORT).show();
                        flag=true;
                    }

                }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_back:
            startActivity(new Intent(this, Games.class));
            break;
        }
    }
}
