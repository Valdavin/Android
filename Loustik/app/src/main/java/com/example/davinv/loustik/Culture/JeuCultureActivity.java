package com.example.davinv.loustik.Culture;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.davinv.loustik.R;

import java.util.ArrayList;

public class JeuCultureActivity extends AppCompatActivity {

    // ConstanteS
    private static final String TEST = "test";
    private static final int TPS_ATTENTE = 2000; //temps atendu entre 2 qestion



    private ArrayList<QuestionClass> listeQuestions;
    private String choixDomaine;
    private int questionCour = 0;
    private int score = 0;

    private LinearLayout MainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_culture);

        MainLayout = (LinearLayout) findViewById(R.id.math_main_layout);
        listeQuestions = new ArrayList<>();
        initialiserQuestion();

    }

    private void initialiserQuestion() {
        listeQuestions.add(new QuestionClass());
        listeQuestions.add(new QuestionClass());
        listeQuestions.add(new QuestionClass());
        listeQuestions.add(new QuestionClass());
        listeQuestions.add(new QuestionClass());

    }


    public void jeuMathChoix(View view) {
        switch (view.getId()) {
            case R.id.Jeu_Culture_Test:
                choixDomaine = TEST;
        }

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
            String question = listeQuestions.get(questionCour).getEnonce();

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
                afficherReponses();

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
        bt_suite.setText("Suite");
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

    private void afficherReponses(){
        QuestionClass q = listeQuestions.get(questionCour);
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
}
