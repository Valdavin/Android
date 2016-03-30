package com.example.davinv.loustik.Math;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.davinv.loustik.User.User;
import com.example.davinv.loustik.User.UserDAO;
import com.example.davinv.loustik.LoginActivity;
import com.example.davinv.loustik.R;

public class JeuMathMenuActivity extends AppCompatActivity {

    // CONSTANTES

    private static final int NOMBRE_OPERATIONS = 10;

    // OPERATIONS ELEMENTAIRES
    public static final String OPERATEUR_ADDITION = "+";
    public static final String OPERATEUR_MULTIPLICATION = "x";
    public static final String OPERATEUR_SOUSTRACTION = "-";
    public static final String OPERATEUR_DIVISION = "÷";
    private String choixOperateur; // Choix de l'operation élémentaire.

    // STATUT DU LAYOUT (pour pouvoir y retourner en cas de sauvegarde)
    public static final String STATUS_EXERCICE = "Exercice";
    public static final String STATUS_RESULTAT = "Reponse";
    public static final String STATUS_MAIN = "Main";
    private String statusCour;

    //ETATS A SAUVEGARDER
    public static final String STATE_NUMEROPAGECOUR = "numeroPageCour";
    public static final String STATE_SCORE = "score";
    public static final String STATE_USER = "user";
    public static final String STATE_STATUS = "status";
    public static final String STATE_JEUMATH = "jeuMath";
    public static final String STATE_OPERATEUR = "choixOperateur";



    private int rep = 0;            // Réponse proposé
    private int numeroPageCour = 0; // Numéro de l'opération dans la liste
    private int score = 0;          // Score durant cette exercice

    private User u;
    private UserDAO uDAO = new UserDAO(this);


    // MULTIPLICATION
    private static int numeroTabl = 42;

    // CLASSES
    private JeuMathClass Jeu_Math ;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_math);
        Jeu_Math = new JeuMathClass();
        int userNum = getIntent().getIntExtra(LoginActivity.NUM_USER,0);
        uDAO.open();
        u = uDAO.retrieveByID(userNum);
        statusCour = STATUS_MAIN;


    }

    /////////////////////////
    //  GENERALE


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("onSaveInstanceState");
        outState.putInt(STATE_SCORE, score);
        outState.putInt(STATE_USER, u.getId());
        outState.putInt(STATE_NUMEROPAGECOUR,numeroPageCour);
        outState.putString(STATE_STATUS, statusCour);
        outState.putParcelable(STATE_JEUMATH, Jeu_Math);
        outState.putString(STATE_OPERATEUR, choixOperateur);
        super.onSaveInstanceState(outState);


    }
    public void  onRestoreInstanceState (Bundle  savedInstanceState ) {
        // Always call the superclass so it can restore
        // the view hierarchy
        System.out.println("onRestoreInstanceState");
        super.onRestoreInstanceState ( savedInstanceState );
        // Restore state members from saved instance
        score  =  savedInstanceState.getInt(STATE_SCORE);
        u = uDAO.retrieveByID(savedInstanceState.getInt(STATE_USER));
        numeroPageCour  =  savedInstanceState.getInt(STATE_NUMEROPAGECOUR);
        statusCour  =  savedInstanceState.getString(STATE_STATUS);
        Jeu_Math = savedInstanceState.getParcelable(STATE_JEUMATH);
        choixOperateur = savedInstanceState.getString(STATE_OPERATEUR);

        switch (statusCour) {
            case STATUS_EXERCICE:
                viewExerciceMath(numeroPageCour);
                break;
            case STATUS_RESULTAT:
                viewResultat();
                break;
        }
    }

    /**
     * Initialise puis Affiche les exercice en fonction de l'opération élémentaire choisi
     * @param view Opération élémentaire choisi.
     */
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

    /**
     * Corrige les réponse au bout de la liste d'opération.
     */
    public void corriger() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Résultat");
        u.addScore((NOMBRE_OPERATIONS - Jeu_Math.nbrErreur(choixOperateur)) * 10);
        uDAO.update(u);


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

    /**
     * Affiche l'opération numéro <b>numeroPageCour</b>
     */
    public void updateView() {
        viewExerciceMath(numeroPageCour);}




    /////////////////////////
    // MULTIPLICATION


    public void tableMultiplicationValider(View view) {
    }



    //////////////////////////
    //          VUES        //
    //////////////////////////

    /**
     * Affiche l'oppération d'index <b>numDansListe</b>.
     * @param numDansListe numéro de l'opération dans la liste.
     */
    public void viewExerciceMath(final int numDansListe) {
        statusCour = STATUS_EXERCICE;

        //Déclaration et Initialisation variable
        int nbr1 = Jeu_Math.getNumAt(2 * numDansListe);
        int nbr2 = Jeu_Math.getNumAt(2 * numDansListe + 1);
        rep = Jeu_Math.getReponseAt(numDansListe);



        //Génaration de la page
        setContentView(R.layout.activity_jeu_math_exercice);


        //Opération
        TextView twOperation = (TextView)findViewById(R.id.Jeu_Math_Exercice_Operation);
        twOperation.setText(nbr1 + " " + choixOperateur + " " + nbr2 + " = ");


        // Réponse
        final EditText etReponse = (EditText)findViewById(R.id.Jeu_Math_Exercice_Reponse);
        if (rep == 0) {
            etReponse.setText("");
        } else {
            etReponse.setText(String.valueOf(rep));
        }

        etReponse.setInputType(InputType.TYPE_CLASS_NUMBER);
        etReponse.setRawInputType(Configuration.KEYBOARD_12KEY);
        etReponse.requestFocus();




        // Numero de Page
        TextView numPage = (TextView) findViewById(R.id.Jeu_Math_Exercic_NumPage);
        TextView tw1 = new TextView(this);
        numPage.setText(numDansListe + 1 + "/" + NOMBRE_OPERATIONS);



        // BOUTON PRECEDENT ET SUIVANT
        Button btSuiv = (Button) findViewById(R.id.Jeu_Math_Exercice_BoutonSuivant);
        Button btPrec = (Button) findViewById(R.id.Jeu_Math_Exercice_BoutonPrec);

        btPrec.setText("Précedent");
        if (numDansListe == 0) {
            btPrec.setVisibility(View.INVISIBLE);
            btPrec.setClickable(false);
        }
        btPrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etReponse.getText().toString().equals("")) {
                    Jeu_Math.setReponseAt(numeroPageCour, Integer.parseInt(etReponse.getText().toString()));


                } else {
                    Jeu_Math.setReponseAt(numeroPageCour, 0);

                }
                numeroPageCour--;
                updateView();

            }
        });

        if (numDansListe == NOMBRE_OPERATIONS -1) {
            btSuiv.setText("Corriger");
            btSuiv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    corriger();
                }
            });
        } else {
            btSuiv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Faudrait rajouter un truc pour éviter de mettre un nombre trop grand.
                    if (!etReponse.getText().toString().equals("")) {
                        Jeu_Math.setReponseAt(numeroPageCour, Integer.parseInt(etReponse.getText().toString()));


                    } else {
                        Jeu_Math.setReponseAt(numeroPageCour, 0);

                    }
                    numeroPageCour++;
                    updateView();

                }
            });
        }
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(this.getCurrentFocus(), InputMethodManager.SHOW_FORCED);
    }

    /**
     * Affiche la vue des résultats.
     */
    public void viewResultat() {
        statusCour = STATUS_RESULTAT;
        System.out.println("VIEW RESULTAT");
        setContentView(R.layout.activity_jeu_math_resultat);




        LinearLayout ll_conteneur_rep = (LinearLayout)findViewById(R.id.Jeu_Math_Resultat_Layout);
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

        Button bt = (Button) findViewById(R.id.Jeu_Math_Resultat_BoutonFinir);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusCour = STATUS_MAIN;
                recreate();
            }
        });
    }



    /////////////////////////
    // MULTIPLICATION

    /*
    * Affiche le choix du numéro de la table de multiplication.
    *
     *//*
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



    }*/


    /*
    * Affiche la table de multiplication
    *

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
            et.setInputType(InputType.TYPE_CLASS_TEXT);
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

    }*/
}
