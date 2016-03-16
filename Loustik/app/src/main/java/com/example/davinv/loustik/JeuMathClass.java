package com.example.davinv.loustik;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Valentin on 03/03/2016.
 */
public class JeuMathClass {
    private int nbr_d_adition;
    private ArrayList<Integer> listeAdd = new ArrayList<>(nbr_d_adition*2);
    private ArrayList<Integer> listeReponsesAdd = new ArrayList<>(nbr_d_adition);

    public JeuMathClass() {


    }



    /*
    * Créer une liste contenant nbr_d_adition entiers aléatoirs entre 0 et 10.
    * Ainsi on a nbr_d_adition d'adition
     */
    public void creerAdition(int nbadd) {
        nbr_d_adition = nbadd;
        Random rand = new Random();
        for (int i = 0; i < nbr_d_adition*2; i++) {
            listeAdd.add(rand.nextInt(9)+1);
        }
        for (int i = 0; i < nbr_d_adition; i++) {
            listeReponsesAdd.add(0);
        }
    }

    public void setAddReponse(int numRep, int rep) {
        listeReponsesAdd.set(numRep, rep);
    }

    public int getAddReponse(int numRep) {
        return listeReponsesAdd.get(numRep);
    }

    public int getAddAt(int i) {
        return listeAdd.get(i);
    }

    public boolean estJuste() {
        boolean toutBon = true;
        for (int i = 0; i<nbr_d_adition; i++) {
            System.out.print(listeReponsesAdd.get(i) + " ");
        }
        System.out.println();
        for (int i = 0; i<nbr_d_adition; i++) {
            System.out.print(listeAdd.get(i*2) + " " + listeAdd.get(i*2+1) + "     ");
        }
        System.out.println();

        for (int i =0; i<nbr_d_adition; i++) {
            System.out.print(listeAdd.get(i*2) + " + " + listeAdd.get(i*2+1));
            if(listeAdd.get(i*2)+listeAdd.get(i*2+1) != listeReponsesAdd.get(i)){
                System.out.println(" != " + listeReponsesAdd.get(i));
                toutBon = false;
            } else {
                System.out.println(" = " + listeReponsesAdd.get(i));
            }

        }
        return toutBon;
    }

    public boolean estJusteAt(int i) {
        return listeAdd.get(i*2)+listeAdd.get(i*2+1) == listeReponsesAdd.get(i);
    }

    public int nbrErreur(){
        int erreur = 0;


        for (int i =0; i<nbr_d_adition; i++) {

            if(listeAdd.get(i*2)+listeAdd.get(i*2+1) != listeReponsesAdd.get(i)){
               erreur++;

            }

        }
        return erreur;
    }




}
