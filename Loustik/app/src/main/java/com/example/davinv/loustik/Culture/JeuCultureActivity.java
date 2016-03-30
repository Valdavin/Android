package com.example.davinv.loustik.Culture;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.davinv.loustik.Login.User;
import com.example.davinv.loustik.Login.UserDAO;
import com.example.davinv.loustik.LoginActivity;
import com.example.davinv.loustik.R;
import java.util.ArrayList;
import java.util.Collections;

public class JeuCultureActivity extends AppCompatActivity {

    // Constante
    private static final String TEST = "test";

    // Thèmes
    public static final String THEME_TOUS = "TOUS";
    public static final String THEME_HISOIRE = "HISTOIRE";
    public static final String THEME_GEOGRAPHIE = "GEOGRAPHIE";
    public static final String THEME_GENERALE = "GENERALE";
    public static final String THEME_SCIENCE = "SCIENCE";
    public static final String[] THEME_ARRAY = { THEME_TOUS, THEME_HISOIRE, THEME_GEOGRAPHIE, THEME_GENERALE, THEME_SCIENCE};

    private static final int TPS_ATTENTE = 2000; //temps atendu entre 2 qestion

    public static final String STATE_LISTE_QUESTION = "liste_question";
    public static final String STATE_SCORE = "score";
    public static final String STATE_USER = "user";
    public static final String STATE_STATUS = "status";



    public static final String STATUS_MAIN = "main";
    public static final String STATUS_QUESTION = "question";
    private String statusCour;


    private ArrayList<Question> listeQuestions;
    private String choixDomaine;
    private int score = 0;

    // USER
    private User u;
    private UserDAO uDAO = new UserDAO(this);

    private LinearLayout MainLayout;
    private QuestionDAO QuestionDAO = new QuestionDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_culture);

        MainLayout = (LinearLayout) findViewById(R.id.math_main_layout);
        listeQuestions = new ArrayList<>();
        QuestionDAO.open();

        int userNum = getIntent().getIntExtra(LoginActivity.NUM_USER,0);
        uDAO.open();
        u = uDAO.retrieveByID(userNum);



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("onSaveInstanceState");
        outState.putInt(STATE_SCORE, score);
        outState.putInt(STATE_USER, u.getId());
        outState.putParcelableArrayList(STATE_LISTE_QUESTION, listeQuestions);
        outState.putString(STATE_STATUS, statusCour);
        super.onSaveInstanceState(outState);


    }
    public void  onRestoreInstanceState (Bundle  savedInstanceState ) {
        // Always call the superclass so it can restore
        // the view hierarchy
        System.out.println("onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state members from saved instance
        score  =  savedInstanceState.getInt(STATE_SCORE);
        u = uDAO.retrieveByID(savedInstanceState.getInt(STATE_USER));
        listeQuestions  =  savedInstanceState.getParcelableArrayList(STATE_LISTE_QUESTION);
        statusCour = savedInstanceState.getString(STATE_STATUS);

        switch (statusCour) {
            case STATUS_MAIN:
                break;
            case STATUS_QUESTION:
                setViewQuestion();
                updateContent();

        }

    }


    private void initialiserQuestion() {
        if (choixDomaine != THEME_TOUS) {
            listeQuestions = QuestionDAO.getQuestionParTheme(choixDomaine);
        } else {
            listeQuestions = QuestionDAO.selectAll();
        }
        Collections.shuffle(listeQuestions);


    }


    public void jeuMathChoix(View view) {
        switch (view.getId()) {
            case R.id.Jeu_Culture_Tout:
                choixDomaine = THEME_TOUS;
                break;
            case R.id.Jeu_Culture_Generale:
                choixDomaine = THEME_GENERALE;
                break;
            case R.id.Jeu_Culture_Geographie:
                choixDomaine = THEME_GEOGRAPHIE;
                break;
            case R.id.Jeu_Culture_Histoire:
                choixDomaine = THEME_HISOIRE;
                break;
            case R.id.Jeu_Culture_Science:
                choixDomaine = THEME_SCIENCE;
                break;
        }

        initialiserQuestion();
        setViewQuestion();
        updateContent();
    }

    public void verifReponse(View v) {
        Button bouton = (Button) v;

        if (listeQuestions.get(0).isBonneRep(bouton.getText().toString())) {
            u.addScore(10);
            uDAO.update(u);
            score = score + 10;
        }
    }

    public void updateContent() {

        if (!listeQuestions.isEmpty()) {

            ArrayList<String> listeRep = listeQuestions.get(0).getReponses();
            String question = listeQuestions.get(0).getQuestion();


            TextView et = (TextView) findViewById(R.id.Jeu_Culture_Question_Intitule);
            Button bt1 = (Button) findViewById(R.id.Jeu_Culture_Question_B1);
            Button bt2 = (Button) findViewById(R.id.Jeu_Culture_Question_B2);
            Button bt3 = (Button) findViewById(R.id.Jeu_Culture_Question_B3);

            et.setText(question);
            bt1.setText(listeRep.get(0));
            bt1.setBackgroundResource(android.R.drawable.btn_default);

            bt2.setText(listeRep.get(1));
            bt2.setBackgroundResource(android.R.drawable.btn_default);

            bt3.setText(listeRep.get(2));
            bt3.setBackgroundResource(android.R.drawable.btn_default);

            TextView tw = (TextView) findViewById(R.id.Jeu_Culture_Question_Score);

            tw.setText("Score = "+score);


        } else {
            //Jeu Finis
            super.finish();
        }
    }



    public void proposerQuestion(View view) {
        Intent intent = new Intent(this,PropositionQuestionActivity.class);
        startActivity(intent);
    }





    //////////////////
    // VIEW

    private void setViewQuestion() {
        statusCour = STATUS_QUESTION;
        System.out.println("setViewQuestion");
        setContentView(R.layout.activity_jeu_culture_question);





        View.OnClickListener boutonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifReponse(v);
                setViewAfficherReponses();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        listeQuestions.remove(0);
                        updateContent();
                    }
                }, TPS_ATTENTE);


            }
        };
        Button bt1 = (Button) findViewById(R.id.Jeu_Culture_Question_B1);
        Button bt2 = (Button) findViewById(R.id.Jeu_Culture_Question_B2);
        Button bt3 = (Button) findViewById(R.id.Jeu_Culture_Question_B3);
        Button bt_suite = (Button) findViewById(R.id.Jeu_Culture_Question_Retour);

        bt1.setOnClickListener(boutonClick);
        bt2.setOnClickListener(boutonClick);
        bt3.setOnClickListener(boutonClick);
        bt_suite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusCour = STATUS_MAIN;
                recreate();
            }
        });

        
    }

    private void setViewAfficherReponses(){
        Question q = listeQuestions.get(0);
        Button bt1 = (Button) findViewById(R.id.Jeu_Culture_Question_B1);
        Button bt2 = (Button) findViewById(R.id.Jeu_Culture_Question_B2);
        Button bt3 = (Button) findViewById(R.id.Jeu_Culture_Question_B3);

        if (q.isBonneRep(bt1)) {
            bt1.setBackgroundColor(getResources().getColor(R.color.reponseJuste));
        } else {
            bt1.setBackgroundColor(getResources().getColor(R.color.reponseFausse));
        }

        if (q.isBonneRep(bt2)) {
            bt2.setBackgroundColor(getResources().getColor(R.color.reponseJuste));
        } else {
            bt2.setBackgroundColor(getResources().getColor(R.color.reponseFausse));
        }

        if (q.isBonneRep(bt3)) {
            bt3.setBackgroundColor(getResources().getColor(R.color.reponseJuste));
        } else {
            bt3.setBackgroundColor(getResources().getColor(R.color.reponseFausse));
        }
    }

    private void setViewProposerQuestion() {
        System.out.println("setViewProposerQuestion");
        MainLayout.removeAllViews();
        setContentView(R.layout.activity_proposition_question);

        Spinner themeSpinerView = (Spinner) findViewById(R.id.spinner_theme);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, THEME_ARRAY);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themeSpinerView.setAdapter(arrayAdapter);
    }



}
