package ru.nsu.yukhnina;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Queue;

public class Planer extends Thread {
    private final ServerSocket serverSocket;
    private Queue<QueueElement> tasksQueue;
    private final Socket acceptSocket;
    private boolean result;

    public Planer (Queue<QueueElement> tasksQueue, ServerSocket serverSocket, Socket acceptSocket) {
        this.acceptSocket = acceptSocket;
        this.tasksQueue = tasksQueue;
        this.serverSocket = serverSocket;
        this.result = true;
        start();
    }

    public boolean getResult() {
        return result;
    }

    @Override
    public void run() {
        while (!tasksQueue.isEmpty()) {
            try {
                QueueElement currentTask = tasksQueue.poll();
                OutputStream outputStream = acceptSocket.getOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(outputStream);
                ArrayList<Integer> numbers = currentTask.getArrayList();
                ObjectMapper objectMapper = new ObjectMapper();
                out.writeObject(objectMapper.writeValueAsString(numbers));
                InputStream inputStream = acceptSocket.getInputStream();
                ObjectInputStream in = new ObjectInputStream(inputStream);
                result = (boolean) in.readObject();
                if (!result) {
                    serverSocket.close();
                    break;
                }
            } catch (IOException e) {
                System.out.println("Не получилось отправить данные");
            } catch (ClassNotFoundException e) {
                System.out.println("Не могу получить данные");
            }
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
        }
    }
}
