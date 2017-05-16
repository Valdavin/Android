package com.example.valentin.endyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valentin.endyearproject.ListProducts.ListProductsActivity;
import com.example.valentin.endyearproject.product.Product;
import com.example.valentin.endyearproject.product.ProductDAO;
import com.example.valentin.endyearproject.tescoapi.tescoAPI;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//Tesco API


// on importe les classes IntentIntegrator et IntentResult de la librairie zxing

public class scanner extends AppCompatActivity implements View.OnClickListener {
    private int idUser;
    private int idLp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);
        Intent intent = getIntent();
        idUser = intent.getIntExtra(ListProductsActivity.NUM_USER,0);
        idLp = intent.getIntExtra(ListProductsActivity.NUM_LISTPRODUCTS,0);

        Button mybutton = (Button) findViewById(R.id.scan_button);
        mybutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.scan_button){

            // on lance le scanner au clic sur notre bouton
            new IntentIntegrator(this).initiateScan();


        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        // nous utilisons la classe IntentIntegrator et sa fonction parseActivityResult pour parser le résultat du scan
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {

            // Get the barcode content
            String scanContent = scanningResult.getContents();


            Button scanButton = (Button) findViewById(R.id.scan_button);
            TextView scan_content = (TextView) findViewById(R.id.scanner_barcode_content);
            TextView product_name = (TextView) findViewById(R.id.scanner_name_product);
            TextView product_price = (TextView) findViewById(R.id.scanner_price__product);


            // Display result on TextView
            //scan_content.setText(scanContent);
            tescoAPI tescoAPI = new tescoAPI();
            JSONObject replyProduct = tescoAPI.requestProduct(scanContent);
            if (replyProduct.length() != 0) {
                try {

                    JSONArray productsArray = replyProduct.getJSONArray("products");
                    if (productsArray.length() != 0) {

                        JSONObject product = (JSONObject) productsArray.get(0);
                        String productName = product.getString("description");
                        System.out.println(productName);


                        JSONObject replyGrocery = tescoAPI.requestGrocery(productName.replace(" ", "%20"));
                        String productPrice = ((JSONObject) replyGrocery.getJSONObject("uk").getJSONObject("ghs").getJSONObject("products").getJSONArray("results").get(0)).getString("price");

                        System.out.println(productPrice);

                        product_name.setText(productName);
                        product_price.setText(productPrice);


                        scanButton.setVisibility(View.GONE);
                        scan_content.setText(scanContent);
                        scan_content.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }




        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Aucune donnée reçu!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }


    public void submit(View view) {
        String scan_content = ((TextView) findViewById(R.id.scanner_barcode_content)).getText().toString();
        String product_name = ((TextView) findViewById(R.id.scanner_name_product)).getText().toString();
        String product_price = ((TextView) findViewById(R.id.scanner_price__product)).getText().toString();

        if (!scan_content.equals("") && !product_name.equals("")) {
            Product product = new Product(product_name,scan_content,Float.valueOf(product_price),idLp);
            ProductDAO pDAO= new ProductDAO(this);



            pDAO.open();
            pDAO.insert(product);
            pDAO.close();

            super.finish();
        }

    }


}