package ru.nsu.yukhnina;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerStart implements Runnable {

    private int portId;
    private boolean isPrimeArrayPart;
    public Socket socket;

    public ServerStart(int portId) {
        isPrimeArrayPart = true;
        socket = null;
        this.portId = portId;
    }

    public boolean getResult() {
        return isPrimeArrayPart;
    }

    @Override
    public void run() {
        try {
            this.socket = new Socket("localhost", portId);
            ServerCount server = new ServerCount(portId);
            server.checkIsItPrimeOnServer();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
