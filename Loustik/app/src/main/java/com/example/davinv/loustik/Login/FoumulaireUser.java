package com.example.davinv.loustik.Login;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.davinv.loustik.Math.JeuMathActivity;
import com.example.davinv.loustik.R;

import java.util.ArrayList;
import java.util.Arrays;

public class FoumulaireUser extends AppCompatActivity {
    private ArrayList<String> listeAvatar = new ArrayList<>();
    private LinearLayout layoutAvatar;
    private ImageView avatarSelectione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foumulaire_user);
        layoutAvatar = (LinearLayout) findViewById(R.id.ll_choix_avatar);
        initialiserAvatar();


    }

    private void initialiserAvatar() {
        String[] lista = {"avatar1","avatar2","avatar3","avatar4","avatar5","avatar6","avatar7","avatar8","avatar9"};
        listeAvatar = new ArrayList(Arrays.asList(lista));
        System.out.println("Lol !!!");
        for (String s : listeAvatar) {
            /*
            <ImageView
            android:layout_width="80dp"
            android:layout_height="80dp"
            android:layout_margin="5sp"
            android:src="@drawable/avatar1"/>
             */
            System.out.println(s);
            ImageView iw = new ImageView(this);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(300, 300);
            param.setMargins(5,5,5,5);
            iw.setLayoutParams(param);
            iw.setImageResource(getResources().getIdentifier(s, "drawable", this.getPackageName()));
            iw.setClickable(true);
            iw.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    if (!(avatarSelectione == null)) {
                        avatarSelectione.setBackground(null);
                    }
                    avatarSelectione = (ImageView) v;
                    v.setBackgroundResource(R.drawable.rebordchoix);
                }
            });
            System.out.printf("Prout !!!");
            layoutAvatar.addView(iw);
        }

    }
    private void choixAvatar(View v) {

    }
    public void newUserValider(View view) {

    }
}
