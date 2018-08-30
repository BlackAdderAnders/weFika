package com.blackadder.wefika;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ReciveNotification rn = new ReciveNotification();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    Uplink uplink = new Uplink();
    Date now = new Date();
    CollectToken ct = new CollectToken();
    String Iid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Class.forName("android.os.AsyncTask");
        } catch (ClassNotFoundException e) {}



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText test2 = findViewById(R.id.editText2);
        String time = sdf.format(now);
        test2.setHint(time);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setTitle("weFika");


        ct.onTokenRefresh();
        Iid = ct.getIid();


        String channelId  = getString(R.string.default_notification_channel_id);
        String channelName = getString(R.string.default_notification_channel_name);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        final Intent intentsettings = new Intent(this, SettingsActivity.class);

        //Listener for meny button click
        ImageButton menuButton = (ImageButton) findViewById(R.id.main_screen_top_toolbar_social);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Open menu screen
                startActivity(intentsettings);

            }
        });


    }

    public void doStuff(View view) {

        //TODO get team name from UI, all msg will be sent to team
        String userlst = ct.getTeamMembers("BlackAdder");

        EditText test1 = findViewById(R.id.editText);
        EditText test2 = findViewById(R.id.editText2);

        String testText1 = test1.getText().toString();
        String testText2 = test2.getText().toString();

        if (testText2.isEmpty()) {
            testText2 = sdf.format(now);
        }

        System.out.println("iid: " + Iid);
        System.out.println("token: " + ct.token);
        uplink.sendText(ct.token, "Fika:" + testText2 + " @" + testText1, ct.iid); //userlst instead of ct.iid

        test1.setText("");
        test2.setText("");
    }
}
