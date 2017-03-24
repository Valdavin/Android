package com.example.valentin.endyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.valentin.endyearproject.product.ListProductActivity;

// on importe les classes IntentIntegrator et IntentResult de la librairie zxing

public class MainActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void see_content(View view) {
        Intent intent = new Intent(this,ListProductActivity.class);
        startActivity(intent);
    }

    public void new_content(View view) {
        Intent intent = new Intent(this,scanner.class);
        startActivity(intent);
    }
}