package ru.nsu.yukhnina;

import java.net.Socket;

public class ServerStart implements Runnable {

    private int portId;
    public Socket socket;

    public ServerStart(int portId) {
        socket = null;
        this.portId = portId;
    }


    @Override
    public void run() {
        ServerCount server = new ServerCount(portId);
        System.out.println("Сервер пашет");
        server.checkIsItPrimeOnServer();

    }
}
