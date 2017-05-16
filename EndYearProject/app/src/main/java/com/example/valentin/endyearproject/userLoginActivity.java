package com.example.valentin.endyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.valentin.endyearproject.ListProducts.ListProductsListActivity;
import com.example.valentin.endyearproject.user.User;
import com.example.valentin.endyearproject.user.UserDAO;
import com.example.valentin.endyearproject.user.newUserActivity;

import java.util.ArrayList;

public class userLoginActivity extends AppCompatActivity {

    private static final int NEW_USER_REQUEST = 1;
    public static final String NUM_USER = "newUser";
    private UserDAO uDAO = new UserDAO(this);
    private LinearLayout loginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice);
        loadUsers();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NEW_USER_REQUEST) {
            if (resultCode == RESULT_OK) {
                loadUsers();
            }

        }
    }

    private void loadUsers() {
        loginLayout = (LinearLayout) findViewById(R.id.login_layout);
        loginLayout.removeAllViews();
        uDAO.open();
        ArrayList<User> listUsers = uDAO.selectAll();
        uDAO.close();
        boolean estLePremier = true;
        for (User u : listUsers) {
            if (!estLePremier) {
                addViewUser(u);

            }
            estLePremier = false;

        }
    }

    public void newUser(View view) {
        Intent intent = new Intent(this,newUserActivity.class);
        startActivityForResult(intent, NEW_USER_REQUEST);


    }
    private void connexion(User u) {
        Intent intent = new Intent(this,ListProductsListActivity.class);
        intent.putExtra(NUM_USER,u.getId());
        startActivity(intent);
    }


    public void addViewUser(final User u) {
        loginLayout = (LinearLayout) findViewById(R.id.login_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 5, 5, 5);

        LinearLayout ll = new LinearLayout(this);
        ll.setTag(u.getId());
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(params);
        ll.setClickable(true);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connexion(u);
            }
        });




        TextView tw = new TextView(this);
        tw.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        tw.setTextSize(30);
        tw.setGravity(Gravity.CENTER);
        tw.setText(u.getUserName());


        ll.addView(tw);
        registerForContextMenu(ll);

        loginLayout.addView(ll);
    }



}
