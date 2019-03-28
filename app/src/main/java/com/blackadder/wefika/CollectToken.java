package com.blackadder.wefika;


import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.iid.InstanceIdResult;
import com.google.android.gms.tasks.Task;

import static android.content.ContentValues.TAG;

/**
 * Created by ezbolan on 2017-12-20.
 */

public class CollectToken extends FirebaseMessagingService {

    public String token;
    public String iid;
    Toast toast;
    private Context context;

    public CollectToken(Context context) {
        this.context = context;
    }

    /*
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = null;
        refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        token = refreshedToken;

        //Send this updated token to WEBSERVICE
        // TODO: Send refreshedToken to webservice
    }



    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(token);
    }

    */




    public void onTokenRefresh() {

        //New firebase token collection
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        token = task.getResult().getToken();

                        // Log and toast
                        String msg = String.format("Token: %s", token);
                        Log.d(TAG, msg);
                        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });

        // Get updated InstanceID token.
        String refreshedToken = null;
        refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        token = refreshedToken;

        //Send this updated token to WEBSERVICE
        // TODO: Send refreshedToken to webservice
    }

    public String getIid() {
        return FirebaseInstanceId.getInstance().getId();
    }

    public void signOut(){

    }

    public void subscribeToTopic(){
        //FirebaseMessaging.getInstance().subscribeToTopic("BlackAdder");
    }

    public String getTeamMembers(String team){
        //TODO: connect to webservice and collect all tokens that should recive message

        return "";
    }

}
