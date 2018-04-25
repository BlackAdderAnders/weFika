package com.blackadder.wefika;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    ReciveNotification rn = new ReciveNotification();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    Uplink uplink = new Uplink();
    Date now = new Date();
    CollectToken ct = new CollectToken();
    String Iid;
    private View view;

    Button coffe;
    EditText editLoca;
    EditText editTime;
    EditText editUser;
    EditText editGroup;
    TabLayout tabLayout;
    TabItem tabMain;
    TabItem tabSettings;


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

        ct.onTokenRefresh();
        Iid = ct.getIid();

        coffe       = findViewById(R.id.button2);
        editLoca    = findViewById(R.id.editText);
        editTime    = findViewById(R.id.editText2);
        editUser    = findViewById(R.id.editText3);
        editGroup   = findViewById(R.id.editText4);
        tabLayout   = findViewById(R.id.tabLayout1);
        tabMain     = findViewById(R.id.tabMain);
        tabSettings = findViewById(R.id.tabSettings);

        String channelId  = getString(R.string.default_notification_channel_id);
        String channelName = getString(R.string.default_notification_channel_name);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println(tab.getPosition());
                if (tab.getPosition() == 1) {
                    coffe.setVisibility(INVISIBLE);
                    editLoca.setVisibility(INVISIBLE);
                    editTime.setVisibility(INVISIBLE);

                    editUser.setVisibility(VISIBLE);
                    editGroup.setVisibility(VISIBLE);
                } else {
                    coffe.setVisibility(VISIBLE);
                    editLoca.setVisibility(VISIBLE);
                    editTime.setVisibility(VISIBLE);

                    editUser.setVisibility(INVISIBLE);
                    editGroup.setVisibility(INVISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void doStuff(View view) {

        EditText test1 = findViewById(R.id.editText);
        EditText test2 = findViewById(R.id.editText2);

        String testText1 = test1.getText().toString();
        String testText2 = test2.getText().toString();

        if (testText2.isEmpty()) {
            testText2 = sdf.format(now);
        }

        System.out.println("iid: " + Iid);
        System.out.println("token: " + ct.token);
        uplink.sendText(ct.token, "Fika:" + testText2 + " @" + testText1, ct.iid);

        test1.setText("");
        test2.setText("");
    }



    public void changeViewToSettings(View view) {
        System.out.println("QMASVEN: Settings!");
        /*TabItem.OnClickListener(View view) {
            //changeViewToSettings(view);
            System.out.println("QMASVEN: Settings!");
        }*/
    }

}
