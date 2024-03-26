package ru.nsu.yukhnina;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;

public class Planer extends Thread {
    private final ServerSocket serverSocket;
    private Queue<QueueElement> tasksQueue;
    private final Socket acceptSocket;
    private boolean result;

    public Planer(Queue<QueueElement> tasksQueue, ServerSocket serverSocket, Socket acceptSocket) {
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
        QueueElement q = null;
        while (!tasksQueue.isEmpty()) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(acceptSocket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(acceptSocket.getOutputStream()));
                ObjectMapper objectMapper = new ObjectMapper();
                q = tasksQueue.poll();
                if (q == null) {
                    break;
                }
                out.write(objectMapper.writeValueAsString(q.getArrayList()) + "\n");
                out.flush();
                result = objectMapper.readValue(in.readLine(), Boolean.class);
                if (!result) {
                    serverSocket.close();
                    acceptSocket.close();
                    break;
                }
                q = null;
            } catch (IOException e) {
                System.out.println("Проблемы с accept socket");
                if (q != null) {
                    tasksQueue.add(q);
                }
                try {
                    acceptSocket.close();
                } catch (IOException ex) {
                    System.out.println("Не получилось закрыть accept socket, всё плохо");
                    throw new RuntimeException(ex);
                }
            }
        }
        try {
            acceptSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Не получилось закрыть");
            throw new RuntimeException(e);
        }
    }
}
