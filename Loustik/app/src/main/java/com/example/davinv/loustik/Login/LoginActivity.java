package com.example.davinv.loustik.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.davinv.loustik.Login.FoumulaireUser;
import com.example.davinv.loustik.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void connexion(View view) {
    }

    public void newUser(View view) {
        System.out.println("nouvelle user");
        Intent intent = new Intent(this,FoumulaireUser.class);
        startActivity(intent);

    }

    public void anonyme(View view) {

    }
}
