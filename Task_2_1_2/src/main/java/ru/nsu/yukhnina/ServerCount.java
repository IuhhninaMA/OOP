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

    public void checkIsItPrimeOnServer() throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(portId);
        Socket clientSocket = serverSocket.accept();
        InputStream inputStream = clientSocket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ArrayList<Integer> numbers = (ArrayList<Integer>) objectInputStream.readObject();
        int start = (int) objectInputStream.readObject();
        int end = (int) objectInputStream.readObject();
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
    }
}