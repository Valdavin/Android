package m4104c.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class Exercice4Activity extends AppCompatActivity {
    public static final String EXERCICE_4_PRENOM = "prenom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice4);
    }

    public void Exercice4_valider(View view) {
        TextView prenom_view = (TextView) findViewById(R.id.exercice4_prenom);
        String prenom = prenom_view.getText().toString();

        Intent intent = new Intent(this,HelloActivity.class);

        intent.putExtra(EXERCICE_4_PRENOM, prenom);

        startActivity(intent);

    }


}
