package com.blackadder.wefika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    Uplink uplink = new Uplink();
    Date now = new Date();
    CollectToken ct = new CollectToken();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText test2 = findViewById(R.id.editText2);
        String time = sdf.format(now);
        test2.setHint(time);

        ct.onTokenRefresh();

    }

    public void doStuff(View view) {

        EditText test1 = findViewById(R.id.editText);
        EditText test2 = findViewById(R.id.editText2);

        String testText1 = test1.getText().toString();
        String testText2 = test2.getText().toString();

        if (testText2.isEmpty())  {
            testText2 = sdf.format(now);
        }

        uplink.sendText(ct.token,testText1 + testText2);

        test1.setText("");
        test2.setText("");
    }

}
