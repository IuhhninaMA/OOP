package ru.nsu.yukhnina;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Нить клиента, запускается только после включения соответствующего сервера,
 * отправляет на сервер массивв, начало и конец обрабатываемого кусочка,
 * а потом считывает результат обработки.
 */
public class ClientStart implements Runnable {

    private final int portId;
    private final int start;
    private final int end;
    private final ArrayList<Integer> numbers;
    private boolean isPrimeArrayPart;

    /**
     * @param portId - номн=ер порта по которому сервер соединяется с сокетом,
     * @param start - индекс первого элемента который нужно проверить на простоту,
     * @param end - индекс последнего элемента который нужно проверить,
     * @param numbers - весь массив, кусок которого проверяет эта нить.
     */
    public ClientStart(int portId, int start, int end,
                       ArrayList<Integer> numbers) {
        this.portId = portId;
        this.end = end;
        this.start = start;
        this.numbers = numbers;
        isPrimeArrayPart = true;
    }

    /**
     * getter для получения результата вычисления этой нити.
     */
    public boolean result() {
        return isPrimeArrayPart;
    }

    /**
     * Запуск сокета,
     * подключение к серверу,
     * отправка данных туда и получение результата.
     */
    @Override
    public void run() {
        try {
            Socket socket;
            socket = new Socket("localhost", portId);
            writeDataToServer(socket);
            readDataFromServer(socket);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Проблемки с клиентом");
            throw new RuntimeException(e);
        }
    }

    /**
     * Отправка данных для вычисления на сервер.
     */
    private void writeDataToServer(Socket socket) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(start);
            out.writeObject(end);
            out.writeObject(numbers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Поолучение результата с сервера.
     */
    private void readDataFromServer(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(inputStream);
            isPrimeArrayPart = (boolean) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
