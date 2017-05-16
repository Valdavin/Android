package com.example.valentin.endyearproject.tescoapi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Valentin on 16/05/2017.
 */
// // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)

public class tescoAPI
{
    public static JSONObject requestProduct(String Barcode){
        String urlString = "https://dev.tescolabs.com/product/?gtin="+Barcode;
        String sreply = null;
        JSONObject reply = null;

        try {
            sreply = new APIConnect().execute(urlString).get();
            reply = new JSONObject(sreply);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }




        return reply;

    }

    public static JSONObject requestGrocery(String productName){
        String urlString = "https://dev.tescolabs.com/grocery/products/?query="+productName+"&offset=0&limit=1";

        String sreply = null;
        JSONObject reply = null;

        try {
            sreply = new APIConnect().execute(urlString).get();
            reply = new JSONObject(sreply);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }




        return reply;

    }





}