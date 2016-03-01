package m4104c.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.NumberPicker;


public class Exercice5Activity extends AppCompatActivity {
    public static final String EXERCICE_4_NUMERO = "numero";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice5);
        NumberPicker np = (NumberPicker)  findViewById(R.id.exercice5_numero);
        np.setMinValue(1);
        np.setMaxValue(20);
    }

    public void exercice5_valider(View view) {
        NumberPicker np = (NumberPicker)  findViewById(R.id.exercice5_numero);
        int numero = (int) np.getValue();

        if (numero>0) {
            Intent intent = new Intent(this,TableMultiplicationActivity.class);

            intent.putExtra(EXERCICE_4_NUMERO,numero);

            startActivity(intent);
        }
    }

}
