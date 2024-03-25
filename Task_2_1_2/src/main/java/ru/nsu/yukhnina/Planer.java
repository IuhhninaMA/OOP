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
        while (!tasksQueue.isEmpty()) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(acceptSocket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(acceptSocket.getOutputStream()));
                ObjectMapper objectMapper = new ObjectMapper();
                out.write(objectMapper.writeValueAsString(tasksQueue.poll().getArrayList()) + "\n");
                out.flush();
                result = objectMapper.readValue(in.readLine(), Boolean.class);
                if (!result) {
                    serverSocket.close();
                    acceptSocket.close();
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                serverSocket.close();
                acceptSocket.close();
            } catch (IOException e) {
            }
        }
        try {
            serverSocket.close();
            acceptSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
