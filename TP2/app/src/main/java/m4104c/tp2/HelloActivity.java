package m4104c.tp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class
        HelloActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        String prenom = getIntent().getStringExtra(Exercice4Activity.EXERCICE_4_PRENOM);

        TextView Text_Prenom = (TextView) findViewById(R.id.activity_hello_text);
        Text_Prenom.setText( "Hello " + prenom);
    }

    public void changerPrenom(View view){
        super.finish();
    }

    public void AutreExe(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
