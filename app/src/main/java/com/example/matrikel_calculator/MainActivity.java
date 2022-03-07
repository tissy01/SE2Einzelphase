package com.example.matrikel_calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Raunjak Christian 07.03.2022
 */
public class MainActivity extends AppCompatActivity {

    EditText matrikelnr;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matrikelnr = findViewById(R.id.editTextNumber);
    }

    public void send(View v){
        Send sender = new Send();
        sender.execute(matrikelnr.getText().toString());
    }
}