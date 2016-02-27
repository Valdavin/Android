package m4104c.tp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Exercice2Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_exercice2);
    }

    public void exercice2Valider (View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        TextView   reponse = (TextView) findViewById(R.id.exercice2_message);
        if (radioGroup.getCheckedRadioButtonId() == R.id.radiobutton1) {
            reponse.setText("Bonne Réponse !");
        } else {
            reponse.setText("Mauvaise Réponse ! :(");
        }
    }
}
