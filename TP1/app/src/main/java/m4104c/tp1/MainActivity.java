package m4104c.tp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onExercice1(View view) {

        // Création d'un intent et lancement de la demande de changement d'activity
        Intent Exercice1ViewActivityIntent = new Intent(MainActivity.this, Exercice1Activity.class);
        startActivity(Exercice1ViewActivityIntent);
    }

    public void onExercice2(View view) {

        // Création d'un intent et lancement de la demande de changement d'activity
        Intent Exercice2EventActivityIntent = new Intent(MainActivity.this, Exercice2Activity.class);
        startActivity(Exercice2EventActivityIntent);
    }

    public void onExercice3(View view) {

        // Création d'un intent et lancement de la demande de changement d'activity
        Intent Exercice3EventActivityIntent = new Intent(MainActivity.this, Exercice3Activity.class);
        startActivity(Exercice3EventActivityIntent);
    }
}
