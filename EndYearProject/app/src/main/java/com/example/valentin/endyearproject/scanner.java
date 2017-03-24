package com.example.valentin.endyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valentin.endyearproject.product.Product;
import com.example.valentin.endyearproject.product.ProductDAO;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

// on importe les classes IntentIntegrator et IntentResult de la librairie zxing

public class scanner extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);

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

            // nous récupérons le contenu du code barre
            String scanContent = scanningResult.getContents();


            Button scanButton = (Button) findViewById(R.id.scan_button);
            TextView scan_content = (TextView) findViewById(R.id.scanner_barcode_content);

            // nous affichons le résultat dans nos TextView

            scan_content.setText(scanContent);
            scanButton.setVisibility(View.GONE);
            scan_content.setVisibility(View.VISIBLE);
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

        if (!scan_content.equals("") && !product_name.equals("")) {
            Product product = new Product(product_name,scan_content);
            ProductDAO pDAO= new ProductDAO(this);

            pDAO.open();
            pDAO.insert(product);
            pDAO.close();

            super.finish();
        }

    }
}