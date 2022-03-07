package com.example.matrikel_calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Raunjak Christian 07.03.2022
 */
public class MainActivity extends AppCompatActivity {

    EditText matrikelnr;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        matrikelnr = findViewById(R.id.editTextNumber);

        Thread tr = new Thread(new ServerThread());
        tr.start();
    }
    class ServerThread implements Runnable{

        Socket s;
        ServerSocket ss;
        InputStreamReader ir;
        BufferedReader br;
        Handler h = new Handler();
        String answer;

        @Override
        public void run(){
            {
                try {
                    ss = new ServerSocket(53212);
                    while(true){
                        s = ss.accept();
                        ir = new InputStreamReader(s.getInputStream());
                        br = new BufferedReader(ir);
                        answer = br.readLine();

                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void send(View v){
        Send sender = new Send();
        sender.execute(matrikelnr.getText().toString());
    }
}