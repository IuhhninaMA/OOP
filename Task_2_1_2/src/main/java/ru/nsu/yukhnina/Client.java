package ru.nsu.yukhnina;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Main function, start servers,
 * send data to servers and return result.
 */
public class Client {

    ArrayList<Integer> numbers;
    ArrayList<Socket> sockets;
    ArrayList<ObjectOutputStream> out;
    ArrayList<ObjectInputStream> in;
    int serversCount;
    int minPortId;
    boolean isPrime;

    /**
     * Class constructor,
     * contains array to check,
     * all opened sockets,
     * count servers,
     * start port Id,
     * next ports has Id start+i,
     * in and out - streams.
     */
    public Client (ArrayList<Integer> num, int serversCount) {
        numbers = num;
        sockets = new ArrayList<>();
        this.serversCount = serversCount;
        minPortId = 12345;
        isPrime = true;
        in = new ArrayList<>();
        out = new ArrayList<>();
    }

    /**
     * Main function that start servers,
     * create sockets,
     * send data,
     * rewad and print and return result to user.
     */
    public boolean check() {
        try {
//            runServers();
            createSockets();
            writeData();
            readData();
            closeSockets();
            System.out.println("Массив состоит из простых чисел: " + isPrime);
            return isPrime;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        ArrayList<Integer> k = new ArrayList<>();
    }

    /**
     * Send data on machine.
     */
    private void writeData() throws IOException {
        //пока так добавить разделение данных
        for (int i = 0; i < serversCount; i++) {
            OutputStream outputStream = sockets.get(i).getOutputStream();
            out.add(new ObjectOutputStream(outputStream));
            //передаю масив
            out.get(i).writeObject(numbers);
            //передаю начало куска который нужно проверить
            out.get(i).writeObject(1);
            //передаю конец куска для проверки на этом сервере
            out.get(i).writeObject(2);
            //передаю хэш переданного массива для проверки целостности
            //УДАЛИТЬ
            out.get(i).writeObject(numbers.hashCode());
        }
    }

    /**
     * Create sockets.
     */
    private void createSockets() throws IOException {
        for (int i = 0; i < serversCount; i++) {
            sockets.add(new Socket("localhost", minPortId+i));
        }
    }

    /**
     * Read data from sockets.
     */
    private void readData() throws IOException, ClassNotFoundException {
        for (int i = 0; i < serversCount; i++) {
            InputStream inputStream = sockets.get(i).getInputStream();
            in.add(new ObjectInputStream(inputStream));
            if (!((boolean) in.get(i).readObject())) {
                isPrime = false;
                return;
            }
        }
    }

    /**
     * At the end close all sockets.
     */
    private void closeSockets() throws IOException {
        for (Socket i : sockets) {
            i.close();
        }
    }

    /**
     * Start machine.
     */
//    private void runServers() {
//        for (int i = 0; i < serversCount; i++) {
//            ServerCheckPrime s = new ServerCheckPrime(minPortId+i);
//            s.runServer();
//        }
//    }
}
