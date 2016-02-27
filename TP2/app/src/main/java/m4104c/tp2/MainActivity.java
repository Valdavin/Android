package m4104c.tp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // ID REQUETES
    public final static int EXERCICE_4_ACTIVITY_REQUEST = 3;
    public final static int EXERCICE_5_ACTIVITY_REQUEST = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onExercice4(View view) {

        // Création d'une intention
        Intent intent = new Intent(this, Exercice4Activity.class);

        // Lancement de la demande de changement d'activité
        startActivityForResult(intent, EXERCICE_4_ACTIVITY_REQUEST);
    }

    public void onExercice5(View view) {

        // Création d'une intention
        Intent intent = new Intent(this, Exercice5Activity.class);

        // Lancement de la demande de changement d'activité
        startActivityForResult(intent, EXERCICE_5_ACTIVITY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Vérification du retour à l'aide du code requête
        if (requestCode == EXERCICE_4_ACTIVITY_REQUEST) {

            // Afficher une notification
            String notification = "Retour exercice 4";
            Toast.makeText(this, notification, Toast.LENGTH_SHORT).show();

        } else if (requestCode == EXERCICE_5_ACTIVITY_REQUEST) {

            // Afficher une notification
            String notification =  "Retour exercice 5";
            Toast.makeText(this, notification, Toast.LENGTH_SHORT).show();

        }
    }
}
