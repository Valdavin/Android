package com.example.davinv.loustik.Culture;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Valentin on 18/03/2016.
 */
public class Question implements Parcelable {
    private int id;



    private String question;
    private String reponseVrai;
    private String reponseFausse1;
    private String reponseFausse2;
    private String theme;

    // PARCELABLE
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>()
    {
        @Override
        public Question createFromParcel(Parcel source)
        {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size)
        {
            return new Question[size];
        }
    };

    public Question(Parcel in) {
        this.id = in.readInt();
        this.question = in.readString();
        this.reponseVrai = in.readString();
        this.reponseFausse1 = in.readString();
        this.reponseFausse2 = in.readString();
        this.theme = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(reponseVrai);
        dest.writeString(reponseFausse1);
        dest.writeString(reponseFausse2);
        dest.writeString(theme);
    }


    // GENERALE

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
        theme = JeuCultureActivity.THEME_TOUS;
    }

    public Question(String question, String repV, String repF1, String repF2, String th) {
        this.question = question;
        reponseVrai = repV;
        reponseFausse1 = repF1;
        reponseFausse2 = repF2;
        theme = th;
    }


    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }



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
