package com.example.davinv.loustik.Culture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.davinv.loustik.R;

public class PropositionQuestionActivity extends AppCompatActivity {
    private QuestionDAO QuestionDAO = new QuestionDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition_question);
        QuestionDAO.open();


        Spinner themeSpinerView = (Spinner) findViewById(R.id.spinner_theme);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, JeuCultureActivity.THEME_ARRAY);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themeSpinerView.setAdapter(arrayAdapter);
    }

    public void validerPropQuestion(View view) {
        // On remet les bouton a jour si on s'est trompé avant
        findViewById(R.id.et_propQ_intitule).setBackgroundResource(android.R.drawable.edit_text);
        findViewById(R.id.et_propQ_bRep).setBackgroundResource(android.R.drawable.edit_text);
        findViewById(R.id.et_propQ_mRep1).setBackgroundResource(android.R.drawable.edit_text);
        findViewById(R.id.et_propQ_mRep2).setBackgroundResource(android.R.drawable.edit_text);


        Spinner themeSpinerView = (Spinner) findViewById(R.id.spinner_theme);
        String intitule = ((EditText) findViewById(R.id.et_propQ_intitule)).getText().toString();
        String bRep = ((EditText) findViewById(R.id.et_propQ_bRep)).getText().toString();
        String mRep1 = ((EditText) findViewById(R.id.et_propQ_mRep1)).getText().toString();
        String mRep2 = ((EditText) findViewById(R.id.et_propQ_mRep2)).getText().toString();

        System.out.println(themeSpinerView.getSelectedItem() + " " + intitule);
        if (!intitule.equals("") && !bRep.equals("") && !mRep1.equals("") && !mRep2.equals("")) {

            Question q = new Question(intitule,bRep,mRep1,mRep2,(String) themeSpinerView.getSelectedItem());
            QuestionDAO.insert(q);
            System.out.printf("Insertion réeussi");
            this.finish();

        } else {
            // On met en valeur les case où il manque des informations.
            if (intitule.equals("")) {
                findViewById(R.id.et_propQ_intitule).setBackgroundResource(R.drawable.editextcustom);
            }
            if (bRep.equals("")) {
                findViewById(R.id.et_propQ_bRep).setBackgroundResource(R.drawable.editextcustom);
            }
            if (mRep1.equals("")) {
                findViewById(R.id.et_propQ_mRep1).setBackgroundResource(R.drawable.editextcustom);
            }
            if (mRep2.equals("")) {
                findViewById(R.id.et_propQ_mRep2).setBackgroundResource(R.drawable.editextcustom);
            }
        }


    }

    public void retour(View view) {
        super.finish();
    }
}
