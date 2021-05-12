package com.example.adviseriltc;

import java.util.ArrayList;

public class User {

    public String fullName, nickName, email, pswd;
    public ArrayList<fvdGame> fvdGames;



    public User(String fullName, String nickName, String email, String pswd, ArrayList<fvdGame> fvdGames) {
        this.fullName = fullName;
        this.nickName = nickName;
        this.email = email;
        this.pswd = pswd;
        this.fvdGames=fvdGames;
    }

}
