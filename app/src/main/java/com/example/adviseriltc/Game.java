package com.example.adviseriltc;

public class Game {
    private String name;
    private String tags;
    private String price;
    private int gameResource;

    public Game(String name, String tags, String price, int banner){

        this.name=name;
        this.tags=tags;
        this.price=price;
        this.gameResource=banner;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getGameResource() {
        return this.gameResource;
    }

    public void setGameResource(int gameResource) {
        this.gameResource = gameResource;
    }
}


