package com.example.davinv.loustik.Culture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.davinv.loustik.Math.JeuMathClass;
import com.example.davinv.loustik.R;
import com.example.davinv.loustik.Culture.QuestionClass;

import java.util.ArrayList;

public class JeuCultureActivity extends AppCompatActivity {

    // Constante
    private static final String TEST = "test";

    private ArrayList<QuestionClass> listeQuestions;
    private String choixDomaine;

    private LinearLayout MainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_culture);

        MainLayout = (LinearLayout) findViewById(R.id.math_main_layout);
        initialiserQuestion();

    }

    private void initialiserQuestion() {
        listeQuestions.add(new QuestionClass());

    }


    public void jeuMathChoix(View view) {
        switch (view.getId()) {
            case R.id.Jeu_Culture_Test:
                choixDomaine = TEST;

        }
    }



    //////////////////
    // VIEW

    private void updateView() {
        
    }
}
