package com.example.matrikel_calculator;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Raunjak Christian 07.03.2022
 *
 */
public class Send extends AsyncTask<Void,Void, Void> {

    Socket s;
    DataOutputStream dout;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            s = new Socket("se2-isys.aau.at", 53212);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
