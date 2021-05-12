package com.example.adviseriltc;

public class NewGames_Calendar {
    String title;
    String img;
    String release_date;

    public NewGames_Calendar(){

    }

    public NewGames_Calendar(String title, String img, String release_date){
        this.title=title;
        this.img=img;
        this.release_date=release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}


