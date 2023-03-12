/*
    Christian Raunjak
    01460761
    SE2 SS 2023
    Einzelaufgabe 1
 */

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    //Declare Variables
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
        context = this;
        // set variables to created views
        send = findViewById(R.id.buttonSend);
        input = findViewById(R.id.numberInput);
        response = findViewById(R.id.serverRspOutput);
    }

    public void sendMrtkNr(View view){ // Gets called when clicking on send button, this is defined in the main xml file directly in my button
        String mrtkNr = input.getText().toString(); // Define String from user input
        ServerStream serverStream = new ServerStream();
        serverStream.execute(mrtkNr); // starts server stream
    }

    public class ServerStream extends AsyncTask<String, Void, String> { //Needs to be async because it cannot run on the main thread, because main would need to pause or wait for this

        String res = "";

        @Override
        protected String doInBackground(String... strings) {
            try {
                socket = new Socket("se2-isys.aau.at", 53212);

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeBytes(strings[0]+'\n');
                out.flush();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                res = in.readLine();
                socket.close();
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            return res;
        }
        @Override
        protected void onPostExecute(String res){ //String res is set
            super.onPostExecute(res);
            response.setText(res);
        }
    }



}