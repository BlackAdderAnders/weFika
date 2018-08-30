package com.blackadder.wefika;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("weFikaPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();


        if (pref != null) {
            String name = pref.getString("Username", "Username");//"No name defined" is the default value.
            String team = pref.getString("Teamname","Teamname");
            String pin = pref.getString("Pincode","Pincode");

            if(name.equals("")){
                name = "Username";
            } else if (team.equals("")) {
                team = "Teamname";
            } else if (pin.equals("")) {
                pin = "Pincode";
            }

            TextView username = findViewById(R.id.editText3);
            TextView teamname = findViewById(R.id.editText4);
            TextView pincode = findViewById(R.id.editText5);

            username.setText(name.toString());
            teamname.setText(team.toString());
            pincode.setText(pin.toString());
        }



        //Listener for meny button click
        Button doneButton = (Button) findViewById(R.id.button3);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Store values in backend
                TextView username = findViewById(R.id.editText3);
                TextView teamname = findViewById(R.id.editText4);
                TextView pincode = findViewById(R.id.editText5);
                editor.putString("Username", username.getText().toString());
                editor.putString("Teamname", teamname.getText().toString());
                editor.putString("Pincode", pincode.getText().toString());

                // Save the changes in SharedPreferences
                editor.commit(); // commit changes

                onBackPressed(); //Go back to previus activity

            }
        });
    }
}
