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
        ServerCount server = new ServerCount(portId);
        System.out.println("Сервер пашет");
        server.checkIsItPrimeOnServer();

    }
}
