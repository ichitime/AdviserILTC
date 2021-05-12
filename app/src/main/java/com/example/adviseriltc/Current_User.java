package com.example.adviseriltc;

import android.widget.TextView;

public class Current_User {
    TextView email;

    public Current_User(TextView email){
        this.email=email;
    }

    public TextView getEmail() {
        return email;
    }

    public void setEmail(TextView email) {
        this.email = email;
    }
}
