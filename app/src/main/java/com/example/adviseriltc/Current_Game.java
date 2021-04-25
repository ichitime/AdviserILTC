package com.example.adviseriltc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Current_Game extends AppCompatActivity {

    private TextView textView_title, textView_price;
    Button play;
    VideoView videoView;
    MediaController mediaController;

    public void videoPlay(View v){
        String videoPath="android.resource://" + getPackageName() + "/" +  R.raw.doom;
        Uri uri=Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_game);

        videoView=findViewById(R.id.banner);
        play=findViewById(R.id.like);
        mediaController=new MediaController(this);
        textView_title=findViewById(R.id.title);
        textView_price=findViewById(R.id.price_game);
        Intent intent=getIntent();
        String Title=intent.getExtras().getString("Title");
        String Price=intent.getExtras().getString("Price");

        textView_title.setText(Title);
        textView_price.setText(Price);
    }
}
