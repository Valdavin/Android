package com.example.davinv.loustik;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    public static final String LOUSTIK_MAIN_NUMERO = "numero";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NumberPicker np = (NumberPicker)  findViewById(R.id.exercice5_numero);
        np.setMinValue(1);
        np.setMaxValue(20);
    }

    public void exercice5_valider(View view) {
        NumberPicker np = (NumberPicker)  findViewById(R.id.exercice5_numero);
        int numero = (int) np.getValue();

        if (numero>0) {
            Intent intent = new Intent(this,JeuMath.class);

            intent.putExtra(LOUSTIK_MAIN_NUMERO,numero);

            startActivity(intent);
        }


    }


}
