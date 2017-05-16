package com.example.valentin.endyearproject.tescoapi;

import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Valentin on 16/05/2017.
 */
// // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)

public class APIConnect extends AsyncTask<String, Void, String>
{
    //declare a delegate with type of protocol declared in this task
    private TaskDelegate delegate;

    //here is the task protocol to can delegate on other object
    public interface TaskDelegate {
        //define you method headers to override
        void onTaskEndWithResult(int success);
        void onTaskFinishGettingData(String result);
    }

    protected String doInBackground(String... params) {
        String jsonResponse = "";
        try {
            /************** For getting response from HTTP URL start ***************/
            URL object = new URL(params[0]);

            HttpURLConnection connection = null;

            connection = (HttpURLConnection) object
                    .openConnection();

            // int timeOut = connection.getReadTimeout();
            connection.setReadTimeout(60 * 1000);
            connection.setConnectTimeout(60 * 1000);
            connection.setRequestProperty("Ocp-Apim-Subscription-Key", "26647487f443434a97853552900e404b");
            int responseCode = connection.getResponseCode();
            //String responseMsg = connection.getResponseMessage();

            if (responseCode == 200) {
                InputStream inputStr = connection.getInputStream();
                String encoding = connection.getContentEncoding() == null ? "UTF-8"
                        : connection.getContentEncoding();
                jsonResponse = IOUtils.toString(inputStr, encoding);
                /************** For getting response from HTTP URL end ***************/

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    protected void onPostExecute(String result) {

    }



}