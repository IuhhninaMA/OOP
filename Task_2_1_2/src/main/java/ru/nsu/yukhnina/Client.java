package ru.nsu.yukhnina;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client {

    ArrayList<Integer> numbers;
    public Client (ArrayList<Integer> num) {
        numbers = num;
    }

    public boolean check() {
        try {

            Socket socket1 = new Socket("localhost", 12345);
            Socket socket2 = new Socket("localhost", 12346);
            Socket socket3 = new Socket("localhost", 12347);
            Socket[] socketsArray = {socket1, socket2, socket3};

            OutputStream outputStream1 = socket1.getOutputStream();
            ObjectOutputStream out1 = new ObjectOutputStream(outputStream1);
            OutputStream outputStream2 = socket2.getOutputStream();
            ObjectOutputStream out2 = new ObjectOutputStream(outputStream2);
            OutputStream outputStream3 = socket3.getOutputStream();
            ObjectOutputStream out3 = new ObjectOutputStream(outputStream3);
            OutputStream[] outStreamsArray = {out1, out2, out3};

            //передаю масив
            out1.writeObject(numbers);
            //передаю начало куска который нужно проверить
            out1.writeObject(1);
            //передаю конец куска для проверки на этом сервере
            out1.writeObject(2);
            //передаю хэш переданного массива для проверки целостности
            out1.writeObject(numbers.hashCode());

            out2.writeObject(numbers);
            out2.writeObject(1);
            out2.writeObject(2);
            out2.writeObject(numbers.hashCode());

            out3.writeObject(numbers);
            out3.writeObject(1);
            out3.writeObject(2);
            out3.writeObject(numbers.hashCode());

            InputStream inputStream1 = socket1.getInputStream();
            ObjectInputStream in1 = new ObjectInputStream(inputStream1);
            boolean isprime = (boolean) in1.readObject();
            InputStream inputStream2 = socket2.getInputStream();
            ObjectInputStream in2 = new ObjectInputStream(inputStream2);
            InputStream inputStream3 = socket3.getInputStream();
            ObjectInputStream in3 = new ObjectInputStream(inputStream3);
            InputStream[] inputStreamsArray = {in1, in2, in3};

            System.out.println("Массив состоит из простых чисел: " + isprime);
            socket1.close();
            socket2.close();
            socket3.close();
            return isprime;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        ArrayList<Integer> k = new ArrayList<>();
        k.add(1);
        k.add(4);
        Client c = new Client(k);
        c.check();
    }
}