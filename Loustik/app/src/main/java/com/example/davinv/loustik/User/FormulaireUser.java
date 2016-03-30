package com.example.davinv.loustik.User;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.davinv.loustik.R;

import java.util.ArrayList;
import java.util.Arrays;

public class FormulaireUser extends AppCompatActivity {
    private ArrayList<String> listeAvatar = new ArrayList<>();
    private LinearLayout layoutAvatar;
    private ImageView avatarSelectione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_user);
        layoutAvatar = (LinearLayout) findViewById(R.id.ll_choix_avatar);
        initialiserAvatar();


    }

    /**
     * Initialise la liste des avatar disponible.
     */
    private void initialiserAvatar() {
        String[] lista = {"avatar1","avatar2","avatar3","avatar4","avatar5","avatar6","avatar7","avatar8","avatar9"};
        listeAvatar = new ArrayList(Arrays.asList(lista));
        for (String s : listeAvatar) {
            ImageView iw = new ImageView(this);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(300, 300);
            param.setMargins(5,5,5,5);
            iw.setLayoutParams(param);
            iw.setImageResource(getResources().getIdentifier(s, "drawable", this.getPackageName()));
            iw.setClickable(true);
            iw.setTag(s);
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
            layoutAvatar.addView(iw);
        }

    }

    /**
     * Vérifie que tout les champs sont remplis et valise l'inscription.
     * @param view Bouton appuyé
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void newUserValider(View view) {
        // Maj affichage
        layoutAvatar.setBackground(null);
        findViewById(R.id.newUserNom).setBackgroundResource(android.R.drawable.edit_text);
        findViewById(R.id.newUserPrenom).setBackgroundResource(android.R.drawable.edit_text);


        // Vérif tout les champs sont bons
        String nom = ((EditText) findViewById(R.id.newUserNom)).getText().toString();
        String prenom = ((EditText) findViewById(R.id.newUserPrenom)).getText().toString();
        if (avatarSelectione != null && !nom.equals("") && !prenom.equals("")) {
            User newUser = new User(nom,prenom,(String) avatarSelectione.getTag());
            UserDAO uDAO = new UserDAO(this);
            uDAO.open();
            uDAO.insert(newUser);
            uDAO.close();
            setResult(RESULT_OK);
            super.finish();
        } else {
            if (avatarSelectione == null) {
                layoutAvatar.setBackgroundResource(R.drawable.editextcustom);
            }
            if (nom.equals("")) {
                findViewById(R.id.newUserNom).setBackgroundResource(R.drawable.editextcustom);
            }
            if (prenom.equals("")) {
                findViewById(R.id.newUserPrenom).setBackgroundResource(R.drawable.editextcustom);
            }
        }


    }
}
