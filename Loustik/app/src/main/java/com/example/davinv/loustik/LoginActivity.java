package com.example.davinv.loustik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.davinv.loustik.User.FormulaireUser;
import com.example.davinv.loustik.User.User;
import com.example.davinv.loustik.User.UserDAO;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    public final static int NEW_USER_REQUEST = 0;
    public final static String NUM_USER = "numUser";

    private UserDAO uDAO = new UserDAO(this);
    private ArrayList<User> listeUsers = new ArrayList<>();
    private LinearLayout loginLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginLayout = (LinearLayout) findViewById(R.id.login_layout);
        uDAO.open();
        chargerUsers();
    }


    /**
     * Se connecte au jeu avec l'user <b>u</b>.
     * @param u User voulant se connecter.
     */
    public void connexion(User u) {
        System.out.println("nouvelle user");
        Intent intent = new Intent(this,MenuPrincipalActivity.class);
        intent.putExtra(NUM_USER,u.getId());
        startActivity(intent);
    }

    /**
     * Démare la vue d'inscription.
     * @param view Bouton appuyé
     */
    public void newUser(View view) {
        System.out.println("nouvelle user");
        Intent intent = new Intent(this,FormulaireUser.class);
        startActivityForResult(intent, NEW_USER_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NEW_USER_REQUEST) {
            if (resultCode == RESULT_OK) {
                chargerUsers();
            }

        }
    }

    /**
     * Se connecte en tant qu'anonyme.
     * @param view Buoton appuyé.
     */
    public void anonyme(View view) {
        User Uanonyme = listeUsers.get(0);
        connexion(Uanonyme);

    }

    /**
     * Initialise la liste des User déjà existant et les affichent.
     */
    private void chargerUsers() {
        loginLayout = (LinearLayout) findViewById(R.id.login_layout);
        loginLayout.removeAllViews();
        listeUsers = uDAO.selectAll();
        boolean estLePremier = true;
        for (User u : listeUsers) {
            if (!estLePremier) {
                ajouterViewUser(u);

            }
            estLePremier = false;

        }
    }
    /////////////
    // VIEW

    /**
     * Affiche la carte de visite de l'user.
     * @param u User a afficher.
     */
    public void ajouterViewUser(final User u) {
        loginLayout = (LinearLayout) findViewById(R.id.login_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 5, 5, 5);

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(params);
        ll.setClickable(true);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connexion(u);
            }
        });

        ImageView iv = new ImageView(this);
        iv.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
        iv.setImageResource(getResources().getIdentifier(u.getAvatar(), "drawable", this.getPackageName()));

        TextView tw = new TextView(this);
        tw.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        tw.setTextSize(30);
        tw.setGravity(Gravity.CENTER);
        tw.setText(u.getPrenom() + " " + u.getNom());

        ll.addView(iv);
        ll.addView(tw);

        loginLayout.addView(ll);
    }
}
