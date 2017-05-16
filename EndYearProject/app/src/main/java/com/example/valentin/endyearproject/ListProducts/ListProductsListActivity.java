package com.example.valentin.endyearproject.ListProducts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.valentin.endyearproject.R;
import com.example.valentin.endyearproject.userLoginActivity;

import java.util.ArrayList;

public class ListProductsListActivity extends AppCompatActivity {
    private int idUser;
    public static final String NUM_USER = "newUser";
    public static final String NUM_LISTPRODUCTS = "ListProducts";
    private int numTicket = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product_list);
        Intent intent = getIntent();

        idUser = intent.getIntExtra(userLoginActivity.NUM_USER,0);
        initList();

    }

    private void initList() {
        ListProductsDAO lPDAO = new ListProductsDAO(this);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.listproductlist_listlayout);
        mainLayout.removeAllViews();

        lPDAO.open();
        ArrayList<ListProducts> listProducts = lPDAO.selectByUserId(idUser);
        lPDAO.close();

        numTicket = 1;
        for (ListProducts p :listProducts) {
            addListProduct(p);
            numTicket++;
        }
    }



    private void addListProduct(final ListProducts lp) {
        float d = this.getResources().getDisplayMetrics().density;
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.listproductlist_listlayout);



        // Main LinearLayout
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mainParams.setMargins((int)(10*d), (int)(10*d), (int)(10*d), (int)(10*d));

        LinearLayout ll = new LinearLayout(this);
        ll.setTag(lp.getId());
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(mainParams);
        ll.setClickable(true);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayListProducts(lp);
            }
        });
        ll.setGravity(Gravity.CENTER);


        //Text View for ID
        LinearLayout.LayoutParams paramID = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramID.setMargins((int)(10*d),(int)(10*d),(int)(10*d),(int)(10*d));

        TextView tw_id = new TextView(this);
        tw_id.setLayoutParams(paramID);

        tw_id.setText(String.valueOf(numTicket));
        tw_id.setTextSize(20f);



        //Put all content in the good Layout

        ll.addView(tw_id);


        mainLayout.addView(ll);
    }

    private void displayListProducts(ListProducts lp) {
        Intent intent = new Intent(this,ListProductsActivity.class);
        intent.putExtra(NUM_USER,idUser);
        intent.putExtra(NUM_LISTPRODUCTS,lp.getId());
        startActivity(intent);
    }

    public void newTicket(View view) {
        ListProducts newLP = new ListProducts(idUser);
        ListProductsDAO lpDAO = new ListProductsDAO(this);
        lpDAO.open();
        lpDAO.insert(newLP);
        lpDAO.close();
        initList();

    }
}
