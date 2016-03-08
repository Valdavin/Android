package com.example.davinv.loustik;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

public class JeuMathActivity extends AppCompatActivity {

    public static final String TABLE_MULTIPLICATION_ERREUR = "erreurs";
    public static final int JEU_MATH_ACTIVITY_ERREUR_REQUEST = 0;
    public static int numeroTabl = 42;
    public JeuMathClass Jeu_Math ;
    public LinearLayout MainLayout;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_math);
        Jeu_Math = new JeuMathClass();
        MainLayout = (LinearLayout) findViewById(R.id.math_main_layout);

    }

    /////////////////////////
    // CHOIX GENERALE


    public void test(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Write your message here.");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
        System.out.printf("View id = " + view.getId());
    }

    public void Jeu_Math_Choix(View view) {
        System.out.printf("View id = " + view.getId());
        System.out.printf("Mult id = " + R.id.Jeu_Math_Multiplication);

        if (view.getId() == R.id.Jeu_Math_Multiplication){
            Multiplication();
        } else if (view.getId() == R.id.Jeu_Math_Addition) {
            //Addition
        }
    }






    /////////////////////////
    // MULTIPLICATION

    public void Multiplication() {


        // Choix multiplication
        // Générer via la classe JeuMathClass

        // Afficher la table de X
        View_Choix__Nbr_Multiplication();

    }

    public void TableMultiplication_Valider(View view) {
    }



    /////////////////////////
    // ADDITION









    //////////////////////////
    //          VUES        //
    //////////////////////////

    /*
    * Affiche le choix du numéro de la table de multiplication.
    *
     */
    public void View_Choix__Nbr_Multiplication(){
        MainLayout.removeAllViews();
        TextView tw = new TextView(this);
        tw.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tw.setText("Choisis ta table de multiplication");
        tw.setGravity(Gravity.CENTER);
        tw.setTextSize(24);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.gravity = Gravity.CENTER_HORIZONTAL;

        final NumberPicker np = new NumberPicker(this);
        np.setLayoutParams(params);
        np.setMaxValue(20);
        np.setMinValue(1);

        Button bt = new Button(this);
        bt.setLayoutParams(params);
        bt.setText("VALIDER");
        bt.setTextSize(20);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroTabl = np.getValue();
                afficher_multiplication();
            }
        });

        MainLayout.addView(tw);
        MainLayout.addView(np);
        MainLayout.addView(bt);



    }

    /*
    * Affiche la table de multiplication
    *
     */
    public void afficher_multiplication() {
        MainLayout.removeAllViews();
        for (int i = 0; i<10; i++) {
            LinearLayout ll = new LinearLayout(MainLayout.getContext());
            ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ll.setGravity(Gravity.CENTER);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            TextView tw = new TextView(MainLayout.getContext());
            tw.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            String textMult = numeroTabl + " x " + (i+1) + " = ";
            tw.setText(textMult);
            tw.setTextSize(25);

            EditText et = new EditText(MainLayout.getContext());
            et.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            et.setInputType(InputType.TYPE_CLASS_NUMBER);
            et.setText("0");
            View.OnFocusChangeListener l = new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    EditText edit = (EditText) v;
                    if (hasFocus && edit.getText().toString().equals("0")) {
                        edit.setText("");
                    } else if (edit.getText().toString().equals("")) {
                            System.out.println("--------  Valeur de l'EditText = \""+edit.getText().toString()+"\"");
                            edit.setText("0");
                    } else if (hasFocus && !edit.getText().toString().equals("0")) {
                        edit.setSelection(edit.getText().length());
                    }



                }
            };
            et.setOnFocusChangeListener(l);
            et.setTextSize(15);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_HORIZONTAL;

            Button bt= new Button(this);
            et.setLayoutParams(params);
            et.setText("VALIDER");
            // A AJOUTER :
            // Listener pour valider

            
            ll.addView(tw);
            ll.addView(et);

            MainLayout.addView(ll);




        }

    }
}
