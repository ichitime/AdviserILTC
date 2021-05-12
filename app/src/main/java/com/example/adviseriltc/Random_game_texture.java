package com.example.adviseriltc;

import android.widget.ImageView;
import android.widget.TextView;

public class Random_game_texture {
    TextView title;
    ImageView img;

//    public  Random_game_texture(String img, String title){
//        this.title=title;
//        this.img=img;
//    }

    public Random_game_texture(TextView title, ImageView img) {
        this.title=title;
        this.img=img;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getImg() {
//        return img;
//    }
}
