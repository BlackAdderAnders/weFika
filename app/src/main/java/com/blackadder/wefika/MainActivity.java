package com.blackadder.wefika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doStuff(View view) {
        EditText test1 = findViewById(R.id.editText);
        EditText test2 = findViewById(R.id.editText2);
        String testText = test1.getText().toString();
        test2.setText(testText, TextView.BufferType.NORMAL);
    }

}
