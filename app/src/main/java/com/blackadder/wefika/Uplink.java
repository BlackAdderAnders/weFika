package com.blackadder.wefika;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by erommey on 2017-12-20.
 */

public class Uplink {

    //Variables
    final static private String FCM_URL = "https://fcm.googleapis.com/fcm/send";
    final static private String SERVER_KEY = "AAAA3uhA4V0:APA91bF0ftVUg6BtPbyPOOIcAC2hdKAOSNZhp164u-Ljd85CcuUUrseJHEroO8cg79s1gjDKUKTdY2oQ8IPJCXwWizVsPZ3xX0i62pymkCjUecY30XwhW_sjkeQGhgqGyqaj-eyjFkXJ";

    /**
     * Method to send push notification to Android FireBased Cloud messaging Server.
     *
     * @param tokenId Generated and provided from Android Client Developer
     * @param message which contains actual information.
     */

    static void sendText(String tokenId, String message, String iid) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("fcm.googleapis.com")
                .appendPath("fcm")
                .appendPath("send");


        try {
// Create URL instance.
            //URL url = new URL(FCM_URL);
            URL url = new URL(builder.build().toString());
            System.out.println("URL is: " + url.toString());

// create connection.
            try {

                //Create JSON Object & pass value
                JSONObject infoJson = new JSONObject();

                infoJson.put("body", message);
                infoJson.put("title", "Test send:");

                JSONObject json = new JSONObject();
                json.put("to", tokenId);
                json.put("collapse_key", "type_a");
                json.put("notification", infoJson);

                //Add data to json string
                JSONObject datacon = new JSONObject();
                datacon.put("body", message);
                datacon.put("title", "Team BlackAdder");
                datacon.put("key_1", "Data for key 1");
                datacon.put("key_2", "Hello, test two");

                json.put("data", datacon);

                //Only format test
                System.out.println("json :" + json.toString());
                System.out.println("infoJson :" + infoJson.toString());

                String.valueOf(new AsyncUplink().execute(url.toString(), json.toString()));

        } catch (IllegalStateException es) {
            System.out.print("conn error: " + es.getStackTrace());
        }} catch (JSONException el) {
                System.out.print("url err: " + el.getStackTrace());
            } catch (MalformedURLException er) {
                System.out.print("url err: " + er.getStackTrace());
            }

    }
}