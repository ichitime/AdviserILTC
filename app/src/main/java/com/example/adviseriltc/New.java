package com.example.adviseriltc;

public class New {
    String title;
    String img;
    String description;
    String link;

    public New(){

    }

    public New(String title, String img, String description, String link){
        this.title=title;
        this.img=img;
        this.description=description;
        this.link=link;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
