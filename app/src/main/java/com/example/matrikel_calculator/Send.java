package com.example.matrikel_calculator;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Raunjak Christian 07.03.2022
 *
 */
public class Send extends AsyncTask<String,Void, Void> {

    Socket s;
    DataOutputStream dout;
    PrintWriter pw;

    @Override
    protected Void doInBackground(String... voids) {
        String matrikelnr = voids[0];

        try {
            s = new Socket("se2-isys.aau.at", 53212);
            pw = new PrintWriter(s.getOutputStream());
            pw.write(matrikelnr);
            pw.flush();
            pw.close();
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
