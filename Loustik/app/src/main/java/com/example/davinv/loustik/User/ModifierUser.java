package com.example.davinv.loustik.User;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.davinv.loustik.LoginActivity;
import com.example.davinv.loustik.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ModifierUser extends AppCompatActivity {

    private User u;
    private UserDAO uDAO = new UserDAO(this);
    private ArrayList<String> listeAvatar = new ArrayList<>();
    private LinearLayout layoutAvatar;
    private ImageView avatarSelectione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_user);
        int userNum = getIntent().getIntExtra(LoginActivity.NUM_USER,0);
        layoutAvatar = (LinearLayout) findViewById(R.id.ll_choix_avatar);
        uDAO.open();
        u = uDAO.retrieveByID(userNum);
        uDAO.close();

        initialiserAvatar();
        initialiserChamps();

    }
    private void initialiserAvatar() {
        String[] lista = getResources().getStringArray(R.array.avatar_user);
        listeAvatar = new ArrayList(Arrays.asList(lista));
        for (String s : listeAvatar) {
            ImageView iw = new ImageView(this);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(300, 300);
            param.setMargins(5, 5, 5, 5);
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
            System.out.print("ModifierUser - initialiserAvatar() : Avatar de l'user = " + u.getAvatar() + ", Avatar initialiser = " + s);
            if (s.equals(u.getAvatar())) {
                avatarSelectione = iw;
                iw.setBackgroundResource(R.drawable.rebordchoix);
                System.out.println(" -- Et c'est le bon !");
            } else {
                System.out.println(" -- Et ce n'est pas le bon ...");
            }
            layoutAvatar.addView(iw);
        }

    }

    private void initialiserChamps(){
        TextView prenom = (TextView) findViewById(R.id.newUserPrenom);
        TextView nom = (TextView) findViewById(R.id.newUserNom);
        prenom.setText(u.getPrenom());
        nom.setText(u.getNom());
    }

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
            u.setPrenom(prenom);
            u.setNom(nom);
            u.setAvatar((String) avatarSelectione.getTag());
            UserDAO uDAO = new UserDAO(this);
            uDAO.open();
            uDAO.update(u);
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
