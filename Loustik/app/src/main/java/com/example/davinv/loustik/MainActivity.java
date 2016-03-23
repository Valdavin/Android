package com.example.davinv.loustik;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.davinv.loustik.Culture.JeuCultureActivity;
import com.example.davinv.loustik.Login.LoginActivity;
import com.example.davinv.loustik.Math.JeuMathActivity;
import com.example.davinv.loustik.Login.FoumulaireUser;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,FoumulaireUser.class);
        startActivity(intent);
    }



    public void main_math(View view) {
        Intent intent = new Intent(this,JeuMathActivity.class);
        startActivity(intent);
    }

    public void main_question(View view) {
        Intent intent = new Intent(this,JeuCultureActivity.class);
        startActivity(intent);
    }

    public void anonyme(View view) {
    }
}
