package com.example.davinv.loustik;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.davinv.loustik.Culture.JeuCultureActivity;
import com.example.davinv.loustik.Login.LoginActivity;
import com.example.davinv.loustik.Login.User;
import com.example.davinv.loustik.Login.UserDAO;
import com.example.davinv.loustik.Math.JeuMathActivity;
import com.example.davinv.loustik.Login.FoumulaireUser;


public class MainMenu extends AppCompatActivity {

    User u;
    UserDAO uDAO = new UserDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int userNum = getIntent().getIntExtra(LoginActivity.NUM_USER,0);
        uDAO.open();
        u = uDAO.retrieveByID(userNum);

    }



    private void main_math(View view) {
        Intent intent = new Intent(this,JeuMathActivity.class);
        startActivity(intent);
    }

    private void main_question(View view) {
        Intent intent = new Intent(this,JeuCultureActivity.class);
        startActivity(intent);
    }

    private void setViewUser() {
        LinearLayout userLayout = (LinearLayout) findViewById(R.id.MainMenu_User_Layout);
        ImageView userImage = (ImageView) userLayout.getChildAt(1);
        TextView userName = (TextView) userLayout.getChildAt(2);
        TextView userScore = (TextView) userLayout.getChildAt(3);

        userImage.setImageResource(getResources().getIdentifier(u.getAvatar(), "drawable", this.getPackageName()));
        userName.setText(u.getPrenom() + " " +u.getNom());
        userScore.setText("Score = "+ u.getScore());

    }






}
