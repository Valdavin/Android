package m4104c.tp1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import m4104c.tp1.exemple3.Jeu;
import m4104c.tp1.exemple3.Resultat;


public class Exercice3Activity extends AppCompatActivity {

    private int mainJoueur;
    private int mainOrdinateur;
    public static final int CAILLOUX = 0;
    public static final int CISEAUX = 1;
    public static final int PAPIER = 2;
    private int nombreVictoire=0;
    private int nombreEgalite=0;
    private int nombreDefaite=0;

    private static final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_exercice3);
    }

    public void exercice3Valider(View view) {
        int boutonclique = view.getId();
        initMainOrdinateur();
        TextView res = (TextView) findViewById(R.id.exercice3_resultat);
        switch (boutonclique) {
            case R.id.joueur_papier:
                mainJoueur = PAPIER;
                break;
            case R.id.joueur_ciseaux:
                mainJoueur = CISEAUX;
                break;
            case R.id.joueur_caillou:
                mainJoueur = CAILLOUX;
                break;
        }
        afficherChoixOrdinateur();
        if (egalite()){
            nombreEgalite = nombreEgalite+1;
            TextView def = (TextView) findViewById(R.id.exercice3_égalités);
            res.setText(getResources().getString(R.string.exercice3_egalite));
            def.setText(getResources().getString(R.string.exemple3_egalite)+" "+nombreEgalite);

        } else if (joueurGagne()) {
            nombreVictoire = nombreVictoire+1;
            TextView def = (TextView) findViewById(R.id.exercice3_victoires);
            res.setText(getResources().getString(R.string.exercice3_victoire));
            def.setText(getResources().getString(R.string.exemple3_victoire)+" "+nombreVictoire);
        } else {
            nombreDefaite = nombreDefaite+1;
            TextView def = (TextView) findViewById(R.id.exercice3_defaites);
            res.setText(getResources().getString(R.string.exercice3_defaite));
            def.setText(getResources().getString(R.string.exemple3_defaite)+" "+nombreDefaite);
        }
    }

    private void initMainOrdinateur() {
        mainOrdinateur = random.nextInt(3);
    }

    private void afficherChoixOrdinateur(){
        ImageView ca = (ImageView) findViewById(R.id.ordi_caillou);
        ImageView pa = (ImageView) findViewById(R.id.ordi_papier);
        ImageView ci = (ImageView) findViewById(R.id.ordi_ciseaux);
        cacherChoixOrdinateur();
        switch (mainOrdinateur) {
            case PAPIER:
                pa.setVisibility(View.VISIBLE);
                break;
            case CISEAUX:
                ci.setVisibility(View.VISIBLE);
                break;
            case CAILLOUX:
                ca.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void cacherChoixOrdinateur(){
        ImageView ca = (ImageView) findViewById(R.id.ordi_caillou);
        ImageView pa = (ImageView) findViewById(R.id.ordi_papier);
        ImageView ci = (ImageView) findViewById(R.id.ordi_ciseaux);

        ca.setVisibility(View.INVISIBLE);
        pa.setVisibility(View.INVISIBLE);
        ci.setVisibility(View.INVISIBLE);
    }

    //////////////////////////////////
    public boolean egalite() {
        return mainJoueur == mainOrdinateur;
    }

    public boolean joueurGagne() {
        return (mainJoueur == CAILLOUX && mainOrdinateur == CISEAUX)
                || (mainJoueur == CISEAUX && mainOrdinateur == PAPIER)
                || (mainJoueur == PAPIER && mainOrdinateur == CAILLOUX);
    }

}
