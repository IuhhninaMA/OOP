package ru.nsu.yukhnina;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientStart implements Runnable {

    private final int portId;
    private final int start;
    private final int end;
    private final ArrayList<Integer> numbers;
    public boolean isPrimeArrayPart;

    public ClientStart(int portId, int start, int end,
                       ArrayList<Integer> numbers) {
        this.portId = portId;
        this.end = end;
        this.start = start;
        this.numbers = numbers;
        isPrimeArrayPart = true;
    }

    public boolean result() {
        return isPrimeArrayPart;
    }

    @Override
    public void run() {
        try {
            Socket socket;
            socket = new Socket("localhost", portId);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(start);
            out.writeObject(end);
            out.writeObject(numbers);
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(inputStream);
            isPrimeArrayPart = (boolean) in.readObject();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Проблемки с клиентом");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
