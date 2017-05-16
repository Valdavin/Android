package com.example.valentin.endyearproject.ListProducts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.valentin.endyearproject.R;
import com.example.valentin.endyearproject.product.ProductListActivity;
import com.example.valentin.endyearproject.scanner;


public class ListProductsActivity extends AppCompatActivity {
    private int idUser;
    private int idLp;
    public static final String NUM_USER = "newUser";
    public static final String NUM_LISTPRODUCTS = "ListProducts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);
        Intent intent = getIntent();
        idUser = intent.getIntExtra(ListProductsListActivity.NUM_USER,0);
        idLp = intent.getIntExtra(ListProductsListActivity.NUM_LISTPRODUCTS,0);

    }




    public void see_content(View view) {
        Intent intent = new Intent(this,ProductListActivity.class);
        intent.putExtra(NUM_USER,idUser);
        intent.putExtra(NUM_LISTPRODUCTS,idLp);
        startActivity(intent);
    }

    public void new_content(View view) {
        Intent intent = new Intent(this,scanner.class);
        intent.putExtra(NUM_USER,idUser);
        intent.putExtra(NUM_LISTPRODUCTS,idLp);
        startActivity(intent);
    }

}
