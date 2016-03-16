package com.example.davinv.loustik;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Valentin on 03/03/2016.
 */
public class JeuMathClass {
    private int tailleListeNum;
    private ArrayList<Integer> listeNum = new ArrayList<>(tailleListeNum *2);
    private ArrayList<Integer> listeReponsesAdd = new ArrayList<>(tailleListeNum);

    public JeuMathClass() {


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
        for (int i =0; i< tailleListeNum; i++) {

            if(estJusteAt(i,operateur)){
                toutBon = false;
            }

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




}
