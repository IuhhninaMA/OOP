package ru.nsu.yukhnina;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerStart implements Runnable {

    private int portId;
    private int start;
    private int end;
    private boolean isPrimeArrayPart;
    private ArrayList<Integer> numbers;
    public Socket socket;

    public ServerStart(int portId, int start, int end,
                       ArrayList<Integer> numbers) {
        isPrimeArrayPart = true;
        this.portId = portId;
        this.end = end;
        this.start = start;
        this.numbers = numbers;
        socket = null;
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
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(start);
            out.writeObject(end);
            out.writeObject(numbers);
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(inputStream);
            isPrimeArrayPart = in.readBoolean();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
