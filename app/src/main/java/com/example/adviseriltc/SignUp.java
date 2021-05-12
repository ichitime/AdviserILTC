package com.example.adviseriltc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText editFullName, editNickname, editEmail, editPassword;
    private TextView textSignUpBack, labelName;
    private ArrayList<fvdGame> fvdGamess;
    ArrayList<Test> zaeball;
    private ProgressBar progressBar;
    private Button signUpBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        editFullName = (EditText) findViewById(R.id.full_name_sign_up);
        editNickname = (EditText) findViewById(R.id.nickname_sign_up);
        editEmail = (EditText) findViewById(R.id.email_sign_up);
        editPassword = (EditText) findViewById(R.id.pswd_sign_up);

        signUpBtn = (Button) findViewById(R.id.sign_up_btn);
        signUpBtn.setOnClickListener(this);

        labelName = (TextView) findViewById(R.id.banner_sign_up);
        labelName.setOnClickListener(this);

        ImageButton signInBack = (ImageButton) findViewById(R.id.sign_in_back);
        signInBack.setOnClickListener(this);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        String fullName = editFullName.getText().toString().trim();
        String nickName = editNickname.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        switch(v.getId()) {
            case R.id.sign_up_btn:
                signingUp();
                break;
            case R.id.banner_sign_up:
                startActivity(new Intent(this, MainActivity.class));
            case R.id.sign_in_back:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    public void signingUp() {

        String fullName = editFullName.getText().toString().trim();
        String nickName = editNickname.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        ArrayList<fvdGame> fvdGames= fvdGamess;
        ArrayList<Test> zaebal = zaeball;

        //progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if(fullName.isEmpty()) {
            editFullName.setError("Введите имя!");
            editFullName.requestFocus();
            return;
        }

        if(nickName.isEmpty()) {
            editNickname.setError("Введите никнейм!");
            editNickname.requestFocus();
            return;
        }

        if(email.isEmpty()) {
            editEmail.setError("Введите e-mail!");
            editEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Введите корректный адрес e-mail!");
            editEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            editPassword.setError("Введите пароль!");
            editPassword.requestFocus();
            return;
        }

        if(password.length() < 8) {
            editPassword.setError("Минимальная длина пароля 8 символов!");
            editPassword.requestFocus();
            return;
        }


        //progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            User user = new User(fullName, nickName, email, password, fvdGames);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(com.example.adviseriltc.SignUp.this, "Пользователь зарегистрирован!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignUp.this, News.class));
                                        //progressBar.setVisibility(View.GONE);

                                        //redirect to SingIn layout

                                    }else {
                                        Toast.makeText(com.example.adviseriltc.SignUp.this, "Ошибка!", Toast.LENGTH_SHORT).show();
                                        //progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(com.example.adviseriltc.SignUp.this, "Ошибка", Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

}