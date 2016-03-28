package com.example.davinv.loustik.Login;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davinv.loustik.Login.FoumulaireUser;
import com.example.davinv.loustik.MainMenu;
import com.example.davinv.loustik.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    public final static int NEW_USER_REQUEST = 0;
    public final static String NUM_USER = "numUser";

    private UserDAO uDAO = new UserDAO(this);
    private ArrayList<User> listeUsers;
    private LinearLayout loginLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        listeUsers = new ArrayList<>();
        loginLayout = (LinearLayout) findViewById(R.id.login_layout);
        uDAO.open();
        chargerUsers();
    }

    public void connexion(User u) {
        System.out.println("nouvelle user");
        Intent intent = new Intent(this,MainMenu.class);
        intent.putExtra(NUM_USER,u.getId());
        startActivity(intent);
    }

    public void newUser(View view) {
        System.out.println("nouvelle user");
        Intent intent = new Intent(this,FoumulaireUser.class);
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

    public void anonyme(View view) {

    }

    private void chargerUsers() {
        loginLayout.removeAllViews();
        listeUsers = uDAO.selectAll();

        for (User u : listeUsers) {
            ajouterViewUser(u);
        }
    }
    /////////////
    // VIEW
    public void ajouterViewUser(final User u) {
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
