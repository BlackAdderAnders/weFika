package com.blackadder.wefika;


import android.app.Service;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * Created by ezbolan on 2017-12-20.
 */

public class CollectToken extends FirebaseInstanceIdService {

    public String token;
    public String iid;


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

    public String getIid() {
        return FirebaseInstanceId.getInstance().getId();
    }

    public void signOut(){

    }

}
