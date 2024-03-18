package ru.nsu.yukhnina;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // Отправка данных на несколько серверов
            String[] servers = {"server1.com", "server2.com", "server3.com"};
            for (String server : servers) {
                try (Socket serverSocket = new Socket(server, 8080);
                     PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true)) {
                    out.println("Data from client: " + clientSocket);
                    System.out.println("Data sent to server: " + server);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
