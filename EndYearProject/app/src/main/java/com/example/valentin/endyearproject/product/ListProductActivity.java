package com.example.valentin.endyearproject.product;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.valentin.endyearproject.R;

import java.util.ArrayList;

/**
 * Created by Valentin on 19/03/2017.
 */

public class ListProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_product);
        initList();
    }

    /*
    <LinearLayout
                android:layout_marginLeft="10dp"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">
                <TextView
                    android:text="1"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:textSize="20dp"
                    android:layout_margin="10dp"
                    android:layout_weight="0.2"/>
                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="typical product name"
                    android:layout_margin="10dp"
                    android:textSize="20dp"
                    android:layout_weight="0.4"/>
                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="barcode"
                    android:layout_margin="10dp"
                    android:textSize="20dp"
                    android:layout_weight="0.4"/>


            </LinearLayout>
     */
    private void initList() {
        ProductDAO pDAO = new ProductDAO(this);

        pDAO.open();
        ArrayList<Product> listProducts = pDAO.selectAll();
        pDAO.close();

        for (Product p :listProducts) {
            addProduct(p);
        }
    }

    private void addProduct(final Product p) {
        float d = this.getResources().getDisplayMetrics().density;
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.listproduct_listlayout);



        // Main LinearLayout
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mainParams.setMargins((int)(10*d), (int)(10*d), (int)(10*d), (int)(10*d));

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(mainParams);


        //Text View for ID
        LinearLayout.LayoutParams paramID = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,0.2f);
        paramID.setMargins((int)(10*d),(int)(10*d),(int)(10*d),(int)(10*d));

        TextView tw_id = new TextView(this);
        tw_id.setLayoutParams(paramID);
        tw_id.setText(String.valueOf(p.getId()));
        tw_id.setTextSize(20f);

        //Text View for Product Name
        LinearLayout.LayoutParams paramProductName = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,0.4f);
        paramProductName.setMargins((int)(10*d),(int)(10*d),(int)(10*d),(int)(10*d));

        TextView tw_ProductName = new TextView(this);
        tw_ProductName.setLayoutParams(paramProductName);
        tw_ProductName.setText(p.getProductName());
        tw_ProductName.setTextSize(20f);

        //Text View for Barcode
        LinearLayout.LayoutParams paramBarcode = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,0.4f);
        paramBarcode.setMargins((int)(10*d),(int)(10*d),(int)(10*d),(int)(10*d));

        TextView tw_Barcode = new TextView(this);
        tw_Barcode.setLayoutParams(paramBarcode);
        tw_Barcode.setText(p.getBarcode());
        tw_Barcode.setTextSize(20f);

        //Put all content in the good Layout

        ll.addView(tw_id);
        ll.addView(tw_Barcode);
        ll.addView(tw_ProductName);

        mainLayout.addView(ll);



    }
}
