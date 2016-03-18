package com.example.davinv.loustik.Culture;

import android.widget.Button;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Valentin on 18/03/2016.
 */
public class QuestionClass {
    private String enonce;
    private String reponseVrai;
    private String reponseFausse1;
    private String reponseFausse2;

    public QuestionClass() {
        this.enonce = "De quel couleur est le cheval blanc d'Henri IV ?";
        reponseVrai = "Blanc";
        reponseFausse1 = "Bleu";
        reponseFausse2 = "Jaune fluo"; // C'es très le swag
    }

    public QuestionClass(String intitule, String repV, String repF1, String repF2) {
        this.enonce = intitule;
        reponseVrai = repV;
        reponseFausse1 = repF1;
        reponseFausse2 = repF2;
    }

    public String getEnonce() {
        return enonce;
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
