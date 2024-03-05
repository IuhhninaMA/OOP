package ru.nsu.yukhnina;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Checking part of array is it contain only prime numbers.
 */
public class ServerCheckPrime {

    static int portId;

    /**
     * Save only port id.
     */
    public ServerCheckPrime(int socketPort) {
        portId = socketPort;
    }

    /**
     * Start server and count data.
     */
    public static void main(String[] arg) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Сервер запущен. Ожидание подключения...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Клиент подключился.");

            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            ArrayList<Integer> numbers = (ArrayList<Integer>) objectInputStream.readObject();
            int start = (int) objectInputStream.readObject();
            int end = (int) objectInputStream.readObject();

            //проверю целостность данных,
            //если данные пришли кривые то отправляю false
            int hash = (int) objectInputStream.readObject();
            if (numbers.hashCode() != hash) {
                OutputStream outputStream = clientSocket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(false);
            }

            boolean isSorted = isPrimeArray(numbers, start, end);

            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(isSorted);

            clientSocket.close();
            serverSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check array part.
     */
    private static boolean isPrimeArray(ArrayList<Integer>numbers, int start, int end) {
        for (int i = start; i < end; i++) {
            if (isPrimeNum(numbers.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check one num.
     */
    public static boolean isPrimeNum(int n) {
        int maxPossibleDivider = (((int) Math.sqrt(n) + 1));
        for (int j = 2; j <= maxPossibleDivider; j++) {
            if (n % j == 0 && j != n) {
                return true;
            }
        }
        return false;
    }
}