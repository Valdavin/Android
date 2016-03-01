package m4104c.tp2;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.TypeVariable;

public class TableMultiplicationActivity extends AppCompatActivity {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public static int numeroTabl;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_multiplication);


        numeroTabl = getIntent().getIntExtra(Exercice5Activity.EXERCICE_4_NUMERO, 42);

        LinearLayout MainLayout = (LinearLayout) findViewById(R.id.table_multiplication_layout);
        for (int i = 0; i<10; i++) {
            LinearLayout ll = new LinearLayout(this);
            ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ll.setGravity(Gravity.CENTER);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            TextView tw = new TextView(this);
            tw.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            String textMult = numeroTabl + " x " + (i+1) + " = ";
            tw.setText(textMult);
            tw.setTextSize(25);

            EditText et = new EditText(this);
            et.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            et.setInputType(InputType.TYPE_CLASS_NUMBER);
            et.setText("0");
            et.setTextSize(15);

            ll.addView(tw);
            ll.addView(et);

            MainLayout.addView(ll);




        }
    }

    public void TableMultiplication_Valider(View view) {
        int erreur = 0;
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.table_multiplication_layout);
        for (int i = 0; i<10; i++){
            LinearLayout ln = (LinearLayout) mainLayout.getChildAt(i+1);
            EditText et = (EditText) ln.getChildAt(1);

            int numero = Integer.parseInt(et.getText().toString());

            if (numero == (i+1)*numeroTabl){
                
            }


        }
    }
}
