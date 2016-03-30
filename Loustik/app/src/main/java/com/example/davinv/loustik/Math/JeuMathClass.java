package com.example.davinv.loustik.Math;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Valentin on 03/03/2016.
 */
public class JeuMathClass implements Parcelable{
    private int tailleListeNum;
    private ArrayList<Integer> listeNum = new ArrayList<>(tailleListeNum *2);
    private ArrayList<Integer> listeReponsesAdd = new ArrayList<>(tailleListeNum);

    public static final Parcelable.Creator<JeuMathClass> CREATOR = new Parcelable.Creator<JeuMathClass>()
    {
        @Override
        public JeuMathClass createFromParcel(Parcel source)
        {
            return new JeuMathClass();
        }

        @Override
        public JeuMathClass[] newArray(int size)
        {
            return new JeuMathClass[size];
        }
    };
    public JeuMathClass()  {    }

    public void initialiserListeNumDivision (int nbIter, int min, int max) {
        tailleListeNum = nbIter;
        Random rand = new Random();
        for (int i = 0; i < tailleListeNum; i++) {
            listeReponsesAdd.add(0);
        }

        for (int i = 0; i < tailleListeNum *2; i++) {
            listeNum.add(rand.nextInt(max-min) + min);
        }
        for (int i = 0; i < tailleListeNum; i++) {
            int nb1Temp = listeNum.get(2*i);
            int nb2Temp = listeNum.get(2*i+1);
            listeNum.set(2 * i, nb1Temp * nb2Temp);

        }

    }

    public void initialiserListeNum(int nbIter, int min, int max) {
        tailleListeNum = nbIter;
        Random rand = new Random();
        for (int i = 0; i < tailleListeNum *2; i++) {
            listeNum.add(rand.nextInt(max-min) + min);
        }
        for (int i = 0; i < tailleListeNum; i++) {
            listeReponsesAdd.add(0);
        }
    }

    public void setReponseAt(int numRep, int rep) {
        listeReponsesAdd.set(numRep, rep);
    }

    public int getReponseAt(int numRep) {
        return listeReponsesAdd.get(numRep);
    }

    public int getNumAt(int i) {
        return listeNum.get(i);
    }

    public boolean estJuste(String operateur) {
        boolean toutBon = true;
        for (int i = 0; i<tailleListeNum*2; i=i+2) {
            System.out.print(listeNum.get(i) + " " + operateur +  " " + listeNum.get(i+1));
        }
        System.out.println();

        for (int i = 0; i<tailleListeNum; i=i+1) {
            System.out.print(listeReponsesAdd.get(i)+" ");
        }
        System.out.println();

        for (int i =0; i< tailleListeNum; i++) {
            System.out.print(listeNum.get(2*i) + " " +  + listeNum.get(2*i+1) + operateur);
            if(!estJusteAt(i,operateur)){
                System.out.print(" != ");
                toutBon = false;
            } else {
                System.out.print(" = ");
            }
            System.out.println(listeReponsesAdd.get(i));

        }


        return toutBon;
    }

    public boolean estJusteAt(int i, String operateur) {
        switch (operateur) {
            case "+":
                return listeNum.get(i*2)+listeNum.get(i*2+1) == listeReponsesAdd.get(i);
            case "-":
                return listeNum.get(i*2)-listeNum.get(i*2+1) == listeReponsesAdd.get(i);
            case "÷":
                return listeNum.get(i*2)/listeNum.get(i*2+1) == listeReponsesAdd.get(i);
            case "x":
                return listeNum.get(i*2)*listeNum.get(i*2+1) == listeReponsesAdd.get(i);
            default:
                return false;
        }

    }

    public int nbrErreur(String operateur){
        int erreur = 0;


        for (int i =0; i< tailleListeNum; i++) {

            if(!estJusteAt(i,operateur)){
               erreur++;

            }

        }
        return erreur;
    }

    public int getBonneReponse(int i, String operateur) {
        switch (operateur) {
            case "+":
                return listeNum.get(i*2)+listeNum.get(i*2+1);
            case "-":
                return listeNum.get(i*2)-listeNum.get(i*2+1);
            case "÷":
                return listeNum.get(i*2)/listeNum.get(i*2+1);
            case "x":
                return listeNum.get(i*2)*listeNum.get(i*2+1);
            default:
                return 42;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(tailleListeNum);
        dest.writeList(listeNum);
        dest.writeList(listeReponsesAdd);
    }
}
