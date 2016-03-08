package com.example.davinv.loustik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

public class JeuMath extends AppCompatActivity {

    public static final String TABLE_MULTIPLICATION_ERREUR = "erreurs";
    public static final int EXERCICE_5_ERREUR_REQUEST = 0;
    public static int numeroTabl = 42;
    public JeuAddition Jeu_Math ;
    public LinearLayout MainLayout = (LinearLayout) findViewById(R.id.math_main_layout);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_math);
        Jeu_Math = new JeuAddition();


    }

    public void Multiplication() {


        Jeu_Math.afficher_multiplication(numeroTabl,MainLayout);
    }

    public void TableMultiplication_Valider(View view) {
    }


    public void Jeu_Math_Choix(View view) {
        if (view.getId() == R.id.Jeu_Math_Multiplication){
            Multiplication();
        } else if (view.getId() == R.id.Jeu_Math_Addition) {
            //Addition
        }
    }




    // VUES

    public int View_Choix_Mult(){
        /*

        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
    android:layout_height="match_parent" android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin"
    tools:context="m4104c.tp2.Exercice5Activity"
    android:orientation="vertical">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Choisis ta table de multiplication"
        android:gravity="center"
        android:textSize="24dp"/>

    <NumberPicker
        android:id="@+id/exercice5_numero"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center">
    </NumberPicker>

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:text="VALIDER"
        android:textSize="20sp"
        android:onClick="exercice5_valider"/>

</LinearLayout>
         */
        TextView tw = new TextView(this);
        tw.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tw.setText("Choisis ta table de multiplication");
        tw.setGravity(Gravity.CENTER);
        tw.setTextSize(24);

        NumberPicker np = new NumberPicker(this);
        np.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        np.setGravity(Gravity.CENTER);
        


        return -1;
    }
}
