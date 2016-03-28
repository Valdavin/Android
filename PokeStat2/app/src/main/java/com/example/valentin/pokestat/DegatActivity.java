package com.example.valentin.pokestat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DegatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degat);
    }
    public void degatCalculer(View view) {
        findViewById(R.id.degat_niveau).setBackgroundResource(android.R.drawable.edit_text);
        findViewById(R.id.degat_attaque).setBackgroundResource(android.R.drawable.edit_text);
        findViewById(R.id.degat_puissance).setBackgroundResource(android.R.drawable.edit_text);
        findViewById(R.id.degat_defense).setBackgroundResource(android.R.drawable.edit_text);
        findViewById(R.id.degat_coef).setBackgroundResource(android.R.drawable.edit_text);

        String sniveau = ((EditText) findViewById(R.id.degat_niveau)).getText().toString();
        String sattaque = ((EditText) findViewById(R.id.degat_attaque)).getText().toString();
        String spuissance = ((EditText) findViewById(R.id.degat_puissance)).getText().toString();
        String sdefense = ((EditText) findViewById(R.id.degat_defense)).getText().toString();
        String scoef = ((EditText) findViewById(R.id.degat_coef)).getText().toString();

        if (!sniveau.equals("") && !sattaque.equals("") && !spuissance.equals("") && !sdefense.equals("") && !scoef.equals("")){
            double niveau,attaque, puissance, defense,coef;
            niveau = Double.parseDouble(sniveau);
            attaque = Double.parseDouble(sattaque);
            puissance = Double.parseDouble(spuissance);
            defense = Double.parseDouble(sdefense);
            coef = Double.parseDouble(scoef);

            double value = (((niveau*0.4+2)*attaque*puissance)/(defense*50))*coef;

            BigDecimal bd = new BigDecimal(value);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            TextView tw = (TextView)findViewById(R.id.degat_edittext_result);
            tw.setText(String.valueOf(bd));


        } else {
            if (sniveau.equals("")) {
                findViewById(R.id.degat_niveau).setBackgroundResource(R.drawable.editextcustom);
            }
            if (sattaque.equals("")) {
                findViewById(R.id.degat_attaque).setBackgroundResource(R.drawable.editextcustom);
            }
            if (spuissance.equals("")) {
                findViewById(R.id.degat_puissance).setBackgroundResource(R.drawable.editextcustom);
            }
            if (sdefense.equals("")) {
                findViewById(R.id.degat_defense).setBackgroundResource(R.drawable.editextcustom);
            }
            if (scoef.equals("")) {
                findViewById(R.id.degat_coef).setBackgroundResource(R.drawable.editextcustom);
            }
        }
    }

}
