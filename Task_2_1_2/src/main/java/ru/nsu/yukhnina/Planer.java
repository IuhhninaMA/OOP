package ru.nsu.yukhnina;

import com.google.gson.Gson;

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
                // и отправлять
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(acceptSocket.getOutputStream()));
                Gson gson = new Gson();
                out.write(gson.toJson(tasksQueue.poll().getArrayList()) + "\n");
                out.flush();
                result = gson.fromJson(in.readLine(), Boolean.class);
                if (!result) {
                    serverSocket.close();
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
            }
        }
    }
}
