package com.blackadder.wefika;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ezbolan on 2018-01-10.
 */

public class AsyncUplink extends AsyncTask<String, String, String> {

    private AsyncResponse listener; //The listener interface
    HttpURLConnection conn = null; //New object HttpURLConnection



    public AsyncUplink(AsyncResponse listener){
        this.listener=listener;
        //Params you need in this class
    }

    public AsyncUplink() {

    }

    protected void onPreExecute() {
        //to do before execute
    }


    @Override
    protected String doInBackground(String... params) {

        String response = "";
        String responseError = "";

        try{
            //Connect to URL
            Log.d("JSON", "Start of connexion");
            URL url = new URL(params[0]);
            int responseCode = getHttpConnection(params[0], params[1]);

            //HTTP_OK --> 200
            //HTTP_CONFLICT --> 409
            if (responseCode == 200 ) {
                System.out.println("ResponsCode: 200");
            }
            else if(responseCode == 409){
                System.out.println("ResponsCode: 409");
            }
            else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                conn.disconnect();
            }

        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        /*
        try{
            super.onPostExecute(result);
            listener.onTaskCompleted(result);
        }
        catch(NullPointerException npe){
            Log.d("NullPointerException", npe.getMessage());
        }
        */
    }

    private int getHttpConnection(String... params) throws IOException {
        InputStream stream = null;
        URL url = new URL(params[0]);
        URLConnection connection = url.openConnection();
        int responsCode = 0;
        try {
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Authorization", "key=AIzaSyCkoFjFwDTtK2Qeb6_qjJm537JFXkm_DIg");
            httpConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            httpConnection.setDoOutput(true);
            httpConnection.connect();
            //post
            System.out.println("Connection established...");
            OutputStreamWriter writer = new OutputStreamWriter(httpConnection.getOutputStream());
            //String urlParameters = "name="+name;
            writer.write(params[1]);
            writer.flush();
            responsCode = httpConnection.getResponseCode();
            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                stream = httpConnection.getInputStream();
            }
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return responsCode;
    }
}
