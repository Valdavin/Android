package com.example.davinv.loustik;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

public class JeuMathActivity extends AppCompatActivity {

    // CONSTANTES

    private static final int NOMBRE_OPERATIONS = 10;

    public static final String OPERATEUR_ADDITION = "+";
    public static final String OPERATEUR_MULTIPLICATION = "x";
    public static final String OPERATEUR_SOUSTRACTION = "-";
    public static final String OPERATEUR_DIVISION = "÷";



    private String choixOperateur;
    private int rep = 0;
    private int numeroPageCour = 0;

    // MULTIPLICATION
    private static int numeroTabl = 42;

    // CLASSES
    private JeuMathClass Jeu_Math ;
    private LinearLayout MainLayout;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_math);
        Jeu_Math = new JeuMathClass();
        MainLayout = (LinearLayout) findViewById(R.id.math_main_layout);


    }

    /////////////////////////
    //  GENERALE


    public void jeuMathChoix(View view) {

        switch(view.getId()) {
            case R.id.Jeu_Math_Multiplication:
                choixOperateur = OPERATEUR_MULTIPLICATION;
                Jeu_Math.initialiserListeNum(NOMBRE_OPERATIONS, 1, 10);
                updateView();
                break;
            case R.id.Jeu_Math_Addition:
                choixOperateur = OPERATEUR_ADDITION;
                Jeu_Math.initialiserListeNum(NOMBRE_OPERATIONS, 1, 10);
                updateView();
                break;
            case R.id.Jeu_Math_Soustraction:
                choixOperateur = OPERATEUR_SOUSTRACTION;
                Jeu_Math.initialiserListeNum(NOMBRE_OPERATIONS,1,10);
                updateView();
                break;

            case R.id.Jeu_Math_Division:
                choixOperateur = OPERATEUR_DIVISION;
                Jeu_Math.initialiserListeNumDivision(NOMBRE_OPERATIONS,2,10);
                updateView();
                break;

        }

    }

    public void corriger() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Résultat");

        if (!Jeu_Math.estJuste(choixOperateur)) {
            int nbrerreur = Jeu_Math.nbrErreur(choixOperateur);
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

    public void updateView() {
        viewExerciceMath(numeroPageCour);}




    /////////////////////////
    // MULTIPLICATION


    public void tableMultiplicationValider(View view) {
    }



    //////////////////////////
    //          VUES        //
    //////////////////////////

    public void viewExerciceMath(final int numAddDansListe) {
        //Déclaration et Initialisation variable
        int nbr1 = Jeu_Math.getNumAt(2 * numAddDansListe);
        int nbr2 = Jeu_Math.getNumAt(2 * numAddDansListe + 1);
        rep = Jeu_Math.getReponseAt(numAddDansListe);



        //Génaration de la page
        MainLayout.removeAllViews();


        LinearLayout ln1 = new LinearLayout(this);
        ln1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
        ln1.setGravity(Gravity.CENTER);
        ln1.setOrientation(LinearLayout.HORIZONTAL);

        TextView ln1_tw1 = new TextView(this);
        ln1_tw1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ln1_tw1.setTextSize(50);
        ln1_tw1.setText(nbr1 +" "+ choixOperateur + " " + nbr2 + " = ");

        final EditText ln1_et1 = new EditText(this);
        ln1_et1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ln1_et1.setTextSize(50);
        if (rep == 0) {
            ln1_et1.setText("");
        } else {
            ln1_et1.setText(String.valueOf(rep));
        }

        ln1_et1.setInputType(InputType.TYPE_CLASS_NUMBER);
        ln1_et1.requestFocus();




        // TEXT POUR NUMERO PAGE
        TextView tw1 = new TextView(this);
        tw1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tw1.setGravity(Gravity.CENTER);
        tw1.setText(numAddDansListe+1 + "/" + NOMBRE_OPERATIONS);


        LinearLayout ln2 = new LinearLayout(this);
        ln2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,0f));
        ln2.setOrientation(LinearLayout.HORIZONTAL);
        ln2.setWeightSum(0);


        // BOUTON PRECEDENT ET SUIVANT
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
                if (!ln1_et1.getText().toString().equals("")) {
                    Jeu_Math.setReponseAt(numeroPageCour, Integer.parseInt(ln1_et1.getText().toString()));


                } else {
                    Jeu_Math.setReponseAt(numeroPageCour, 0);

                }
                numeroPageCour--;
                updateView();

            }
        });

        Button ln2_bt2 = new Button(this);
        ln2_bt2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));
        ln2_bt2.setText("Suivant");
        ln2_bt2.setTextSize(20);
        if (numAddDansListe == NOMBRE_OPERATIONS -1) {
            ln2_bt2.setText("Corriger");
            ln2_bt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                    corriger();
                }
            });
        } else {
            ln2_bt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!ln1_et1.getText().toString().equals("")) {
                        Jeu_Math.setReponseAt(numeroPageCour, Integer.parseInt(ln1_et1.getText().toString()));


                    } else {
                        Jeu_Math.setReponseAt(numeroPageCour, 0);

                    }
                    numeroPageCour++;
                    updateView();

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



        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(this.getCurrentFocus(), InputMethodManager.SHOW_FORCED);


    }

    public void viewResultat() {
        System.out.println("VIEW RESULTAT");
        MainLayout.removeAllViews();




        LinearLayout ll_conteneur_rep = new LinearLayout(this);
        ll_conteneur_rep.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
        ll_conteneur_rep.setOrientation(LinearLayout.VERTICAL);



        for (int i=0; i < NOMBRE_OPERATIONS; i++) {
            LinearLayout ll_rep = new LinearLayout(this);
            ll_rep.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ll_rep.setOrientation(LinearLayout.HORIZONTAL);
            ll_rep.setGravity(Gravity.CENTER_HORIZONTAL);

            TextView tw_addition = new TextView(this);
            tw_addition.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tw_addition.setText(Jeu_Math.getNumAt(2 * i) + " " + choixOperateur + " " + Jeu_Math.getNumAt(2 * i + 1) + " = ");
            tw_addition.setTextSize(30);

            TextView tw_propose = new TextView(this);
            tw_propose.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tw_propose.setText(" " + Jeu_Math.getReponseAt(i) + " ");
            tw_propose.setTextSize(30);

            TextView tw_reponse = new TextView(this);
            tw_reponse.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tw_reponse.setTextSize(30);

            if (!Jeu_Math.estJusteAt(i, choixOperateur)) {
                tw_propose.setTextColor(getResources().getColor(R.color.reponseFausse));
                if (String.valueOf(Jeu_Math.getBonneReponse(i, choixOperateur)).length() > 1) {
                    tw_reponse.setText("    "+Jeu_Math.getBonneReponse(i, choixOperateur)+"  ");
                } else {
                    tw_reponse.setText("     "+Jeu_Math.getBonneReponse(i, choixOperateur)+"  ");
                }

                tw_reponse.setTextColor(getResources().getColor(R.color.reponseJuste));
            } else {
                tw_propose.setTextColor(getResources().getColor(R.color.reponseJuste));
                tw_reponse.setText("        ");

            }
            ll_rep.addView(tw_addition);
            ll_rep.addView(tw_propose);
            ll_rep.addView(tw_reponse);

            ll_conteneur_rep.addView(ll_rep);
        }

        Button bt = new Button(this);
        LinearLayout.LayoutParams ma_param = new  LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0f);
        ma_param.gravity = Gravity.CENTER;

        bt.setLayoutParams(ma_param);
        bt.setText("Finir");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        MainLayout.addView(ll_conteneur_rep);
        MainLayout.addView(bt);


        /*
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
    public void viewChoixNbrMultiplication(){
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
                viewTableMultiplication();
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
    public void viewTableMultiplication() {
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
