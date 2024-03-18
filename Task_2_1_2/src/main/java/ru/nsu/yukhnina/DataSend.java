package ru.nsu.yukhnina;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class DataSend extends Thread {

    ArrayList<Integer> numbers;
    Socket socket;
    int start;
    int end;

    boolean isPrime;
    boolean isFinalCount;
    public DataSend(ArrayList<Integer> numbers, Socket socket, int currentIndex, int countNumbers) {
        this.numbers = numbers;
        this.socket = socket;
        start = currentIndex;
        end = currentIndex + countNumbers;
        start();
        isPrime = false;
        isFinalCount = false;
    }

    public boolean isPrime() {
        return isPrime;
    }
    public boolean isFinalCount() {
        return isPrime;
    }

    public void setStart(int start, int step) {
        this.start = start;
        this.end = start+step;
        start();
    }

    @Override
    public void run() {

        try {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(start);
            out.writeObject(end);
            out.writeObject(numbers);
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(inputStream);
            isPrime = (boolean) in.readObject();
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("Ааааааа, всё плохо плохо плохо плохо");
                throw new RuntimeException(ex);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Не получилось прочитать");
            throw new RuntimeException(e);
        }
        isFinalCount = true;
    }
}
