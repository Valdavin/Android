package com.example.davinv.loustik;

import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Valentin on 03/03/2016.
 */
public class JeuMathClass {

    public JeuMathClass() {

    }



    /*
    * Créer une liste contenant nbr_d_adition entiers aléatoirs entre 0 et 10.
    * Ainsi on a nbr_d_adition d'adition
     */
    public ArrayList<Integer> creerAdition(int nbr_d_adition) {
        ArrayList<Integer> liste_num = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < nbr_d_adition*2; i++) {
           liste_num.add(rand.nextInt(10));
        }
        return liste_num;
    }




}
