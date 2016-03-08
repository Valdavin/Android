package com.example.davinv.loustik;

import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

/**
 * Created by Valentin on 03/03/2016.
 */
public class JeuAddition {

    public JeuAddition() {

    }

    public void afficher_addition(View view) {

    }


    public void afficher_multiplication(int x, LinearLayout MainLayout) {


        for (int i = 0; i<10; i++) {
            LinearLayout ll = new LinearLayout(MainLayout.getContext());
            ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ll.setGravity(Gravity.CENTER);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            TextView tw = new TextView(MainLayout.getContext());
            tw.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            String textMult = x + " x " + (i+1) + " = ";
            tw.setText(textMult);
            tw.setTextSize(25);

            EditText et = new EditText(MainLayout.getContext());
            et.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            et.setInputType(InputType.TYPE_CLASS_NUMBER);
            et.setText("0");
            View.OnFocusChangeListener l = new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    EditText edit = (EditText) v;
                    if (hasFocus) {
                        edit.setText("");
                    } else {
                        if (edit.getText().toString().equals("")) {
                            System.out.println("--------  Valeur de l'EditText = \""+edit.getText().toString()+"\"");
                            edit.setText("0");
                        }
                    }



                }
            };
            et.setOnFocusChangeListener(l);
            et.setTextSize(15);

            ll.addView(tw);
            ll.addView(et);

            MainLayout.addView(ll);




        }

    }

}
