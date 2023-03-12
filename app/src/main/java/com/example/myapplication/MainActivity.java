/*
    Christian Raunjak
    01460761
    SE2 SS 2023
    Einzelaufgabe 1
 */

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
        calc = findViewById(R.id.calculate);

        calc.setOnClickListener(this::alternatingSum);
    }

    public void sendMrtkNr(View view) { // Gets called when clicking on send button, this is defined in the main xml file directly in my button
        String mrtkNr = input.getText().toString(); // Define String from user input
        ServerStream serverStream = new ServerStream();
        serverStream.execute(mrtkNr); // starts server stream
    }

    @SuppressLint("StaticFieldLeak")
    public class ServerStream extends AsyncTask<String, Void, String> { //Needs to be async because it cannot run on the main thread, because main would need to pause or wait for this

        String res = "";

        @Override
        protected String doInBackground(String... strings) {
            try {
                socket = new Socket("se2-isys.aau.at", 53212);

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeBytes(strings[0] + '\n');
                out.flush();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                res = in.readLine();
                socket.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String res) { //String res is set
            super.onPostExecute(res);
            response.setText(res);
        }


    }

    /*
        2.2 MatrNr: 01460761 mod 7 = 1, Alternierende Quersumme bilden und ausgeben, ob diese gerade oder ungerade ist
     */
    public void alternatingSum(View view){
        try {
            int mrtkNr = Integer.parseInt(input.getText().toString());
            int len = input.getText().toString().length();
            int sum = mrtkNr%10;
            mrtkNr /= 10;
            String evenOdd = "";

            for (int i = len-2; i >= 0; i--){
                if(i%2 == 0){
                    sum += mrtkNr%10;
                }
                else {
                    sum -= mrtkNr%10;
                }
                mrtkNr /= 10;
            }
            if (sum %2 == 0){
                evenOdd = " and the sum is an even number!";
            }else{
                evenOdd = " and the sum is an odd number!";
            }
            String res = String.valueOf(sum);
            response.setText("The sum is "+res+evenOdd);
        }catch (NumberFormatException klaus){
            response.setText("Invalid Input");
        }

    }


}