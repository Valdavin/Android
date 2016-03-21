package com.example.davinv.loustik.Culture;

import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Valentin on 18/03/2016.
 */
public class Question implements Serializable {
    private int id;



    private String question;
    private String reponseVrai;
    private String reponseFausse1;
    private String reponseFausse2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReponseVrai() {
        return reponseVrai;
    }

    public String getReponseFausse1() {
        return reponseFausse1;
    }

    public String getReponseFausse2() {
        return reponseFausse2;
    }

    public String getQuestion() {
        return question;
    }

    public void setReponseVrai(String reponseVrai) {
        this.reponseVrai = reponseVrai;
    }

    public void setReponseFausse1(String reponseFausse1) {
        this.reponseFausse1 = reponseFausse1;
    }

    public void setReponseFausse2(String reponseFausse2) {
        this.reponseFausse2 = reponseFausse2;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Question() {
        this.question = "De quel couleur est le cheval blanc d'Henri IV ?";
        reponseVrai = "Blanc";
        reponseFausse1 = "Bleu";
        reponseFausse2 = "Jaune fluo"; // C'es très le swag
    }

    public Question(String question, String repV, String repF1, String repF2) {
        this.question = question;
        reponseVrai = repV;
        reponseFausse1 = repF1;
        reponseFausse2 = repF2;
    }

    public ArrayList<String> getReponses() {
        ArrayList<String> listeRep = new ArrayList<String>(3);
        listeRep.add(reponseVrai);
        listeRep.add(reponseFausse1);
        listeRep.add(reponseFausse2);

        Collections.shuffle(listeRep);

        return listeRep;
    }

    public boolean isBonneRep(String rep) {
        return reponseVrai.equals(rep);
    }

    public boolean isBonneRep(Button boutton) {
        return isBonneRep(boutton.getText().toString());
    }


}
