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

    /**
     * Initialise JeuMathClass avec une liste de <b>nbIter</b> nombres de tels sorte
     * qu'une division renvois une réponse entière entre <b>min</b> et <b>max</b>.
     * @param nbIter  Nombre d'opération.
     * @param min Nombre minimum.
     * @param max Nombre maximum.
     */
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

    /**
     * Initialise JeuMathClass avec une liste de nbIter nombres
     * entre min et max.
     * @param nbIter  Nombre d'opération
     * @param min Nombre minimum
     * @param max Nombre maximum
     */
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

    /**
     * Enregistre la répnse rep dans la liste de réponse à l'indice numRep.
     * @param numRep Indice de la réponse.
     * @param rep réponse donnée.
     */
    public void setReponseAt(int numRep, int rep) {
        listeReponsesAdd.set(numRep, rep);
    }

    /**
     * Renvois la réponse contenus dans la liste de réponses à l'indice <b>numRep</b>
     * @param numRep indice de la réponse
     * @return Réponse à l'indice numRep
     */
    public int getReponseAt(int numRep) {
        return listeReponsesAdd.get(numRep);
    }

    /**
     * Renvois un nombre (opérande) depuis la liste de opérations.
     * @param i indice du l'opérande
     * @return L'opérande à l'index <b>i</b>
     */
    public int getNumAt(int i) {
        return listeNum.get(i);
    }

    /**
     * Vérifie si la liste des réponse est juste en regard
     * de la liste des opérande ainsi que de l'opération élémentaire souhaité
     * @param operateur type d'opération élémentaire
     * @return vrai si toute la liste réponse est juste.
     */
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

    /**
     * Vérifie si l'opération numéro i possède une bonne réponse.
     * @param i Index de la réponse.
     * @param operateur Opération élémentaire souhaitée.
     * @return Vrai si la réponse d'index i est juste.
     */
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

    /**
     * Retourne le nombre d'erreur fait dans la liste d'opération.
     * @param operateur Opération élémentaire souhaitée.
     * @return Le nombre d'erreur(s) commis.
     */
    public int nbrErreur(String operateur){
        int erreur = 0;


        for (int i =0; i< tailleListeNum; i++) {

            if(!estJusteAt(i,operateur)){
               erreur++;

            }

        }
        return erreur;
    }

    /**
     * Renvois la réponse juste à l'opération d'indice <b>i</b>
     * @param i Index de l'opération
     * @param operateur Opération élémentaire souhaitée.
     * @return La bonne réponse pour l'opération d'index <b>i</b>.
     */
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
                return 42; //Au cas où
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
