package com.example.adviseriltc;

import java.util.Comparator;

public class Game {
    String title;
    String tag1;
    String tag2;
    String tag3;
    String tag4;
    String price;
    String image;
    String link;
    String video;
    String release_date;
    String publisher;
    String description;
    String developer;
    String genre;

    public Game(){
    }

    public Game(String title, String tag1, String tag2, String tag3, String tag4,  String price, String image, String link, String video, String release_date, String publisher, String description, String developer, String genre) {
        this.title=title;
        this.tag1=tag1;
        this.tag2=tag2;
        this.tag3=tag3;
        this.tag4=tag4;
        this.price=price;
        this.image=image;
        this.link=link;
        this.video=video;
        this.release_date=release_date;
        this.publisher=publisher;
        this.description=description;
        this.developer=developer;
        this.genre=genre;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getTag4() {
        return tag4;
    }

    public void setTag4(String tag4) {
        this.tag4 = tag4;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public static final Comparator<Game> BY_TITLE_ASCENDING = new Comparator<Game>() {
        @Override
        public int compare(Game o1, Game o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public static final Comparator<Game> BY_TITLE_DESCENDING = new Comparator<Game>() {
        @Override
        public int compare(Game o1, Game o2) {
            return o2.getTitle().compareTo(o1.getTitle());
        }
    };
}