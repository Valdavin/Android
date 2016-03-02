package m4104c.tp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loose);
        int erreur = getIntent().getIntExtra(TableMultiplicationActivity.TABLE_MULTIPLICATION_ERREUR,-1);

        TextView txt = (TextView) findViewById(R.id.text_defaite);
        txt.setText("Dommage, vous avez fait "+erreur+" erreurs");

    }


    public void RetoursMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void CorrigerFaute(View view) {
        setResult(RESULT_OK);
        super.finish();
    }
}
