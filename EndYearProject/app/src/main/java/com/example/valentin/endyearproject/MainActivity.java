package com.example.valentin.endyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

// on importe les classes IntentIntegrator et IntentResult de la librairie zxing

public class MainActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product_list);
        Intent intent = new Intent(this,userLoginActivity.class);
        startActivity(intent);

    }


    public void see_content(View view) {

    }

    public void new_content(View view) {
        Intent intent = new Intent(this,scanner.class);
        startActivity(intent);
    }
}