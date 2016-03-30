package com.example.davinv.loustik;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davinv.loustik.Culture.JeuCultureActivity;
import com.example.davinv.loustik.User.User;
import com.example.davinv.loustik.User.UserDAO;
import com.example.davinv.loustik.Math.JeuMathMenuActivity;


public class MenuPrincipalActivity extends AppCompatActivity {

    private User u;
    private UserDAO uDAO = new UserDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int userNum = getIntent().getIntExtra(LoginActivity.NUM_USER,0);
        uDAO.open();
        u = uDAO.retrieveByID(userNum);
        setViewUser();

    }

    @Override
    protected void onResume() {
        System.out.println("Resume du menu");
        u = uDAO.retrieveByID(u.getId());
        super.onResume();
        setViewUser();
    }

    /**
     * Démarre le jeu de Math.
     * @param view Bouton appuyé.
     */
    public void main_math(View view) {
        Intent intent = new Intent(this,JeuMathMenuActivity.class);
        intent.putExtra(LoginActivity.NUM_USER,u.getId());
        startActivity(intent);
    }

    /**
     * Démare le jeu de Culture
     * @param view Bouton appuyé.
     */
    public void main_question(View view) {
        Intent intent = new Intent(this,JeuCultureActivity.class);
        intent.putExtra(LoginActivity.NUM_USER,u.getId());
        startActivity(intent);
    }

    /**
     * Affiche les information sur l'user connecté.
     */
    private void setViewUser() {
        ImageView userImage = (ImageView) findViewById(R.id.MainMenu_User_Image);
        TextView userName = (TextView) findViewById(R.id.MainMenu_User_Name);
        TextView userScore = (TextView) findViewById(R.id.MainMenu_User_Score);

        userImage.setImageResource(getResources().getIdentifier(u.getAvatar(), "drawable", this.getPackageName()));
        userName.setText(u.getPrenom() + " " +u.getNom());
        userScore.setText("Score = "+ u.getScore());

    }



}
