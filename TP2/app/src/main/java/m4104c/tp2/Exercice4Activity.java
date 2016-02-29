package m4104c.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Exercice4Activity extends AppCompatActivity {
    public static final String EXERCICE_4_PRENOM = "prenom";
    public static final int EXERCICE_4_HELLO_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice4);
    }

    public void Exercice4_valider(View view) {
        EditText prenom_view = (EditText) findViewById(R.id.exercice4_prenom);
        String prenom = prenom_view.getText().toString();

        Intent intent = new Intent(this,HelloActivity.class);

        intent.putExtra(EXERCICE_4_PRENOM, prenom);

        startActivityForResult(intent, EXERCICE_4_HELLO_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EXERCICE_4_HELLO_REQUEST) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "retour ok", Toast.LENGTH_SHORT).show();
                EditText prenom_view = (EditText) findViewById(R.id.exercice4_prenom);
                prenom_view.getText().clear();

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "retour cancel", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
