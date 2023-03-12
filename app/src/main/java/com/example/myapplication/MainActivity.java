/*
    Christian Raunjak
    01460761
    SE2 SS 2023
    Einzelaufgabe 1
 */

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Context context;
    Button send;
    Button calc;
    Socket socket;
    EditText input;
    TextView response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}