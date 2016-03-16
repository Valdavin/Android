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

    // CONSTANTES
    public static final String TABLE_MULTIPLICATION_ERREUR = "erreurs";
    public static final int JEU_MATH_ACTIVITY_ERREUR_REQUEST = 0;
    public static final int NOMBRE_D_ADDITION = 10;

    //ADDITION
    public int rep = 0;
    public int numeroPageCour = 0;

    // MULTIPLICATION
    public static int numeroTabl = 42;

    // CLASSES
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

        if (view.getId() == R.id.Jeu_Math_Multiplication){
            Multiplication();
        } else if (view.getId() == R.id.Jeu_Math_Addition) {
            Jeu_Math.creerAdition(NOMBRE_D_ADDITION);
            Addition();
        }
    }






    /////////////////////////
    // MULTIPLICATION

    public void Multiplication() {


        // Choix multiplication
        // Générer via la classe JeuMathClass

        // Afficher la table de X
        view_Choix__Nbr_Multiplication();

    }

    public void TableMultiplication_Valider(View view) {
    }



    /////////////////////////
    // ADDITION


    public void Addition() {
        viewAddition(numeroPageCour);
    }

    public void CorrigerAddition() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Résultat");

        if (!Jeu_Math.estJuste()) {
            int nbrerreur = Jeu_Math.nbrErreur();
            builder.setMessage("Aoutch, tu as fait " + nbrerreur + " erreur(s)")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Passer", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    })
                    .setNeutralButton("Voir", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            viewResultat();

                        }
                    });


        } else {
            builder.setMessage("Bravo ! Tu n'as pas fait d'erreur !")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Content", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    });

        }
        builder.show();

    }






    //////////////////////////
    //          VUES        //
    //////////////////////////


    /////////////////////////
    // ADDITION


    public void viewAddition(final int numAddDansListe) {
        int nbr1 = Jeu_Math.getAddAt(2*numAddDansListe);
        int nbr2 = Jeu_Math.getAddAt(2 * numAddDansListe + 1);
        rep = Jeu_Math.getAddReponse(numAddDansListe);


        MainLayout.removeAllViews();


        LinearLayout ln1 = new LinearLayout(this);
        ln1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
        ln1.setGravity(Gravity.CENTER);
        ln1.setOrientation(LinearLayout.HORIZONTAL);

        TextView ln1_tw1 = new TextView(this);
        ln1_tw1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ln1_tw1.setTextSize(50);
        ln1_tw1.setText(nbr1 + " + " + nbr2 + " = ");

        final EditText ln1_et1 = new EditText(this);
        ln1_et1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ln1_et1.setTextSize(50);
        ln1_et1.setText(String.valueOf(rep));
        ln1_et1.setInputType(InputType.TYPE_CLASS_NUMBER);

        TextView tw1 = new TextView(this);
        tw1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tw1.setGravity(Gravity.CENTER);
        tw1.setText(numAddDansListe+1 + "/" + NOMBRE_D_ADDITION);

        LinearLayout ln2 = new LinearLayout(this);
        ln2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,0f));
        ln2.setOrientation(LinearLayout.HORIZONTAL);
        ln2.setWeightSum(0);

        Button ln2_bt1 = new Button(this);
        ln2_bt1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,0.5f));
        ln2_bt1.setText("Précedent");
        ln2_bt1.setTextSize(20);
        if (numAddDansListe == 0) {
            ln2_bt1.setVisibility(View.INVISIBLE);
            ln2_bt1.setClickable(false);
        }
        ln2_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ln1_et1.getText().toString() != "") {
                    Jeu_Math.setAddReponse(numeroPageCour,Integer.parseInt(ln1_et1.getText().toString()));


                } else {
                    Jeu_Math.setAddReponse(numeroPageCour, 98);

                }
                numeroPageCour--;
                Addition();

            }
        });

        Button ln2_bt2 = new Button(this);
        ln2_bt2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));
        ln2_bt2.setText("Suivant");
        ln2_bt2.setTextSize(20);
        if (numAddDansListe == NOMBRE_D_ADDITION-1) {
            ln2_bt2.setText("Corriger");
            ln2_bt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CorrigerAddition();
                }
            });
        } else {
            // LISTENER BOUTON "SUIVANT"
            ln2_bt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ln1_et1.getText().toString() != "") {
                        Jeu_Math.setAddReponse(numeroPageCour, Integer.parseInt(ln1_et1.getText().toString()));


                    } else {
                        Jeu_Math.setAddReponse(numeroPageCour, 98);

                    }
                    numeroPageCour++;
                    Addition();

                }
            });
        }
        ln1.addView(ln1_tw1);
        ln1.addView(ln1_et1);

        ln2.addView(ln2_bt1);
        ln2.addView(ln2_bt2);

        MainLayout.addView(ln1);
        MainLayout.addView(tw1);
        MainLayout.addView(ln2);






    }

    public void viewResultat() {
        System.out.println("VIEW RESULTAT");
        /*
            <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:layout_weight="1">
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:gravity="center_horizontal">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="5 + 5 = "
                android:textSize="25sp"/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text=" 7 "
                android:textSize="25sp"
                android:textColor="@color/réponseFausse"/>

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="     10  "
                android:textSize="25sp"
                android:textColor="@color/réponseJuste"/>
        </LinearLayout>

    </LinearLayout>



    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="0"
        android:text="Finir"
        android:layout_gravity="center"/>


         */

        

    }



    /////////////////////////
    // MULTIPLICATION
    /*
    * Affiche le choix du numéro de la table de multiplication.
    *
     */
    public void view_Choix__Nbr_Multiplication(){
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
                afficherMultiplication();
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
    public void afficherMultiplication() {
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
            bt.setLayoutParams(params);
            bt.setText("VALIDER");
            // A AJOUTER :
            // Listener pour valider
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


            ll.addView(tw);
            ll.addView(et);

            MainLayout.addView(ll);




        }

    }
}
