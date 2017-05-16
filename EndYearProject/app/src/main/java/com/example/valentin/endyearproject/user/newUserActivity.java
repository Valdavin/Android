package com.example.valentin.endyearproject.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.valentin.endyearproject.R;

public class newUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }

    public void newUserSubmit(View view) {
        String nom = ((EditText) findViewById(R.id.newUsername)).getText().toString();
        if (!nom.equals("")) {
            User newUser = new User(nom);
            UserDAO uDAO = new UserDAO(this);

            uDAO.open();
            uDAO.insert(newUser);
            uDAO.close();

            setResult(RESULT_OK);
            super.finish();
        }
    }
}
