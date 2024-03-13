package ru.nsu.yukhnina;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Реализация всех вычислений.
 */
public class ServerCount {
    int portId;

    /**
     * Пока тут только PortId,
     * потом добавлю start end numbers.
     */
    public ServerCount(int portId) {
        this.portId = portId;
    }

    /**
     * Основная функция, всё принимает, вычисляет и отправляет,
     * выглядит громоздко, нужно менять.
     */
    public void checkIsItPrimeOnServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portId);
            Socket clientSocket = serverSocket.accept();
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
            throw new RuntimeException(e);
        }
    }

}