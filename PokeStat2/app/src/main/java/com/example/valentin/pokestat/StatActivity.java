package com.example.valentin.pokestat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
    }

    public void statCalculer(View view) {
        findViewById(R.id.stat_base).setBackgroundResource(android.R.drawable.edit_text);
        findViewById(R.id.stat_ev).setBackgroundResource(android.R.drawable.edit_text);
        findViewById(R.id.stat_iv).setBackgroundResource(android.R.drawable.edit_text);
        findViewById(R.id.stat_niveau).setBackgroundResource(android.R.drawable.edit_text);

        String sbase, sev, siv, sniveau;
        sbase = ((EditText) findViewById(R.id.stat_base)).getText().toString();
        sev = ((EditText) findViewById(R.id.stat_ev)).getText().toString();
        siv = ((EditText) findViewById(R.id.stat_iv)).getText().toString();
        sniveau = ((EditText) findViewById(R.id.stat_niveau)).getText().toString();

        if (!sbase.equals("") && !sev.equals("") && !siv.equals("") && !sniveau.equals("")){
            double s,base,ev,iv,niveau;
            base = Double.parseDouble(sbase);
            ev = Double.parseDouble(sev);
            iv = Double.parseDouble(siv);
            niveau = Double.parseDouble(sniveau);

            if (((RadioButton)findViewById(R.id.radioButton_pv)).isChecked()) {
                s=niveau+10;
            } else {
                s = 5;
            }

            double ep = (ev/4);
            double tmpNiv = niveau/100;
            double pv = ((2 * base) + iv + ep)*tmpNiv+s;

            System.out.println("((2 x "+base+") + "+iv+" + "+ep+") x ("+niveau+" / "+" 100) +"+s+" = "+pv);
            System.out.println("("+(2*base)+") + "+iv+" + "+ep+") x ("+tmpNiv+") +"+s+" = "+pv);
            System.out.println("("+(2*base+iv+ep)+") x ("+tmpNiv+") +"+s+" = "+pv);

            BigDecimal bd = new BigDecimal(pv);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            TextView tw = (TextView)findViewById(R.id.stat_edittext_result);
            tw.setText(String.valueOf(bd));
        } else {
            if (sbase.equals("")) {
                findViewById(R.id.stat_base).setBackgroundResource(R.drawable.editextcustom);
            }
            if (sev.equals("")) {
                findViewById(R.id.stat_ev).setBackgroundResource(R.drawable.editextcustom);
            }
            if (siv.equals("")) {
                findViewById(R.id.stat_iv).setBackgroundResource(R.drawable.editextcustom);
            }
            if (sniveau.equals("")) {
                findViewById(R.id.stat_niveau).setBackgroundResource(R.drawable.editextcustom);
            }
        }

    }

    public void statRadio(View view) {
        if(view.getId() == R.id.radioButton_pv) {
            ((RadioButton)findViewById(R.id.radioButton_autreStat)).setChecked(false);
            (findViewById(R.id.radioButton_autreStat)).setClickable(true);
            (findViewById(R.id.radioButton_pv)).setClickable(false);
        } else {
            ((RadioButton)findViewById(R.id.radioButton_pv)).setChecked(false);
            ((RadioButton)findViewById(R.id.radioButton_pv)).setClickable(true);
            ((RadioButton)findViewById(R.id.radioButton_autreStat)).setClickable(false);
        }
    }

}
