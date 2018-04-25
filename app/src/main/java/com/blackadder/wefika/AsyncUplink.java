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
            //conn = (HttpURLConnection) url.openConnection();
            //conn.setReadTimeout(15000);
            //conn.setConnectTimeout(15000);
            //conn.connect();

            //int responseCode=conn.getResponseCode();
            //System.out.print("In async: " + params[1]);

            //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            //out.write(params[1]);
            //out.flush();
            //out.close();

            //HTTP_OK --> 200
            //HTTP_CONFLICT --> 409
            if (responseCode == 200 ) {
                //String line;
                //BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                //while ((line=br.readLine()) != null) {
                //    response+=line;
                //}
                //return response;
                System.out.println("ResponsCode: 200");
            }
            else if(responseCode == 409){
                //String line;
                //BufferedReader br=new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                //while ((line=br.readLine()) != null) {
                //    responseError+=line;
                //}
                //return responseError;
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
        System.out.println("In getHTTP Connection");
        System.out.println("Param 0: " + params[0].toString());
        System.out.println("Param 1: " + params[1].toString());
        InputStream stream = null;
        URL url = new URL(params[0]);
        URLConnection connection = url.openConnection();
        int responsCode = 0;
        try {
            System.out.println("Trying to connect...");
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Authorization", "key=AIzaSyCySRnfKh2wQ4Jex-3sSG7uGnhHE1xLB1Q");
            httpConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            httpConnection.setDoOutput(true);
            httpConnection.connect();
            //post
            System.out.println("Connection established...");
            OutputStreamWriter writer = new OutputStreamWriter(httpConnection.getOutputStream());
            //String urlParameters = "name="+name;
            writer.write(params[1]);
            writer.flush();
            System.out.println("writer is flushed.");
            responsCode = httpConnection.getResponseCode();
            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("return value is HTTP OK here.");
                stream = httpConnection.getInputStream();
            }
            writer.close();
        } catch (Exception ex) {
            System.out.println("Caught an error: " + ex.toString());
            ex.printStackTrace();
        }
        System.out.println("Returning from method now.");
        System.out.println("Return value from http was: " + responsCode);
        return responsCode;
    }
}
