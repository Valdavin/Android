package com.example.davinv.loustik.Culture;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private static final String[] THEME_ARRAY = { THEME_TOUS, THEME_HISOIRE, THEME_GEOGRAPHIE, THEME_GENERALE, THEME_SCIENCE};

    private static final int TPS_ATTENTE = 2000; //temps atendu entre 2 qestion

    static final String STATE_QUESTION = "question";



    private ArrayList<Question> listeQuestions;
    private String choixDomaine;
    private int questionCour = 0;
    private int score = 0;

    private Question question;
    private LinearLayout MainLayout;
    private QuestionDAO QuestionDAO = new QuestionDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_culture);

        MainLayout = (LinearLayout) findViewById(R.id.math_main_layout);
        listeQuestions = new ArrayList<>();
        QuestionDAO.open();

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            questionCour = (int) savedInstanceState.getSerializable(STATE_QUESTION);

        } else {
            // Probably initialize members with default values for a new instance

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

        if (listeQuestions.get(questionCour).isBonneRep(bouton.getText().toString())) {
            score = score + 10;
        }
    }

    public void updateContent() {

        if (questionCour < listeQuestions.size()) {

            ArrayList<String> listeRep = listeQuestions.get(questionCour).getReponses();
            String question = listeQuestions.get(questionCour).getQuestion();

            LinearLayout ll = (LinearLayout) MainLayout.getChildAt(0);
            TextView et = (TextView) ll.getChildAt(0);
            Button bt1 = (Button) ll.getChildAt(1);
            Button bt2 = (Button) ll.getChildAt(2);
            Button bt3 = (Button) ll.getChildAt(3);

            et.setText(question);
            bt1.setText(listeRep.get(0));
            bt1.setBackgroundResource(android.R.drawable.btn_default);

            bt2.setText(listeRep.get(1));
            bt2.setBackgroundResource(android.R.drawable.btn_default);

            bt3.setText(listeRep.get(2));
            bt3.setBackgroundResource(android.R.drawable.btn_default);

            LinearLayout ll2 = (LinearLayout) MainLayout.getChildAt(1);
            TextView tw = (TextView) ll2.getChildAt(0);

            tw.setText("Score = "+score);


        } else {
            //Jeu Finis
            super.finish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        // Save the user's current game state
        outState.putSerializable(STATE_QUESTION, questionCour);

        //
        super.onSaveInstanceState(outState);
    }

    public void retour(View view) {
        super.finish();
    }


    public void proposerQuestion(View view) {
        setViewProposerQuestion();
    }

    public void validerPropQuestion(View view) {
        Spinner themeSpinerView = (Spinner) findViewById(R.id.spinner_theme);
        String intitule = ((EditText) findViewById(R.id.et_propQ_intitule)).getText().toString();
        String bRep = ((EditText) findViewById(R.id.et_propQ_bRep)).getText().toString();
        String mRep1 = ((EditText) findViewById(R.id.et_propQ_mRep1)).getText().toString();
        String mRep2 = ((EditText) findViewById(R.id.et_propQ_mRep2)).getText().toString();

        System.out.println(themeSpinerView.getSelectedItem() + " " + intitule);
        if (!intitule.equals("") && !bRep.equals("") && !mRep1.equals("") && !mRep2.equals("")) {
            Question q = new Question()
            QuestionDAO.insert()
        }


    }



    //////////////////
    // VIEW

    private void setViewQuestion() {
        System.out.println("setViewQuestion");
        MainLayout.removeAllViews();

        LinearLayout ll1 = new LinearLayout(this);
        ll1.setOrientation(LinearLayout.VERTICAL);
        ll1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1f));

        TextView tw = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 5, 5, 5);
        tw.setLayoutParams(params);
        tw.setTextSize(25);
        tw.setGravity(Gravity.CENTER);
        tw.setTextAppearance(getApplicationContext(), R.style.boldText);

        Button bt1 = new Button(this);
        bt1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        Button bt2 = new Button(this);
        bt2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        Button bt3 = new Button(this);
        bt3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));



        View.OnClickListener boutonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifReponse(v);
                setViewAfficherReponses();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        questionCour++;
                        updateContent();
                    }
                }, TPS_ATTENTE);


            }
        };

        bt1.setOnClickListener(boutonClick);
        bt2.setOnClickListener(boutonClick);
        bt3.setOnClickListener(boutonClick);


        LinearLayout ll2 = new LinearLayout(this);
        ll2.setOrientation(LinearLayout.VERTICAL);
        ll2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0f));

        TextView tw_score = new TextView(this);
        tw_score.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tw_score.setGravity(Gravity.CENTER);

        Button bt_suite = new Button(this);
        bt_suite.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bt_suite.setText("Retour");
        bt_suite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_jeu_culture);
            }
        });
        bt_suite.setGravity(Gravity.CENTER);


        ll1.addView(tw);
        ll1.addView(bt1);
        ll1.addView(bt2);
        ll1.addView(bt3);

        ll2.addView(tw_score);
        ll2.addView(bt_suite);

        MainLayout.addView(ll1);
        MainLayout.addView(ll2);
        
    }

    private void setViewAfficherReponses(){
        Question q = listeQuestions.get(questionCour);
        LinearLayout ll = (LinearLayout) MainLayout.getChildAt(0);
        Button bt1 = (Button) ll.getChildAt(1);
        Button bt2 = (Button) ll.getChildAt(2);
        Button bt3 = (Button) ll.getChildAt(3);

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
        setContentView(R.layout.proposerquestion);

        Spinner themeSpinerView = (Spinner) findViewById(R.id.spinner_theme);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, THEME_ARRAY);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themeSpinerView.setAdapter(arrayAdapter);
    }



}
