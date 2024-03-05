package ru.nsu.yukhnina;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client {

    ArrayList<Integer> numbers;
    ArrayList<Socket> sockets;
    ArrayList<ObjectOutputStream> out;
    ArrayList<ObjectInputStream> in;
    int serversCount;
    int minPortId;
    boolean isPrime;
    public Client (ArrayList<Integer> num, int serversCount) {
        numbers = num;
        sockets = new ArrayList<>();
        this.serversCount = serversCount;
        int minPortId = 12345;
        isPrime = true;
        in = new ArrayList<>();
    }

    public boolean check() {
        try {
            runServers();
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

    private void createSockets() throws IOException {
        for (int i = 0; i < serversCount; i++) {
            sockets.add(new Socket("localhost", minPortId+i));
        }
    }

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

    private void closeSockets() throws IOException {
        for (Socket i : sockets) {
            i.close();
        }
    }

    private void runServers() {
        for (int i = 0; i < serversCount; i++) {
            ServerCheckPrime s = new ServerCheckPrime(minPortId+i);
            s.runServer();
        }
    }
}
