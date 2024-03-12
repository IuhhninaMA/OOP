package ru.nsu.yukhnina;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerCount {
    int portId;

    public ServerCount(int portId) {
        this.portId = portId;
    }

    public void checkIsItPrimeOnServer() {
        System.out.println("Сервер работает");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portId);
            System.out.println("Сокет сервера включён");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Клиент подключился");
            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            int start = (int) objectInputStream.readObject();
            int end = (int) objectInputStream.readObject();
            ArrayList<Integer> numbers = (ArrayList<Integer>) objectInputStream.readObject();
            for (int i = start; i < end; i++) {
                int maxPossibleDivider = (((int) Math.sqrt(numbers.get(i)) + 1));
                for (int j = 2; j <= maxPossibleDivider; j++) {
                    if (numbers.get(i) % j == 0 && j != numbers.get(i)) {
                        OutputStream outputStream = clientSocket.getOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                        objectOutputStream.writeObject(false);
                        clientSocket.close();
                        serverSocket.close();
                        return;
                    }
                }
            }
            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(true);

            clientSocket.close();
            serverSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ghjernkt");
            throw new RuntimeException(e);
        }

    }
}