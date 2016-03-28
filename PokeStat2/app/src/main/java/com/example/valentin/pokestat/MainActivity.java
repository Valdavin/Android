package com.example.valentin.pokestat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void stat(View view) {
        Intent intent= new Intent(this,StatActivity.class);
        startActivity(intent);
    }

    public void degats(View view) {
        Intent intent= new Intent(this,DegatActivity.class);
        startActivity(intent);
    }

    public void fuite(View view) {
        Intent intent= new Intent(this,DegatActivity.class);
        startActivity(intent);
    }

    public void capture(View view) {
        Intent intent= new Intent(this,DegatActivity.class);
        startActivity(intent);
    }


    // STAT



    // DEGATS



}
