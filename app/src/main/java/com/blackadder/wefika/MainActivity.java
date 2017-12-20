package com.blackadder.wefika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Uplink uplink = new Uplink();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void doStuff(View view) {
        EditText test1 = findViewById(R.id.editText);
        EditText test2 = findViewById(R.id.editText2);

        String testText1 = test1.getText().toString();
        String testText2 = test2.getText().toString();
        test2.setText(testText1 + testText2, TextView.BufferType.NORMAL);

        uplink.sendText(testText1 + testText2);
    }

}
