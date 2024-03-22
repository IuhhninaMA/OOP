package ru.nsu.yukhnina;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Sender {
    public static void main(String[] args) throws IOException {
        final int SERVER_SOCKET_PORT = 8888;
        final int DATAGRAMM_SOCKET_PORT = 1234;
        final int STEP = 5;
        final ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 3, 5, 7, 8));
        ServerSocket serverSocket = null;
        ArrayList<Planer> threads = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(SERVER_SOCKET_PORT, 50, InetAddress.getByName("localhost"));
            DatagramSocket datagramSocket = new DatagramSocket(DATAGRAMM_SOCKET_PORT);
            byte[] sendDataPort = "8888".getBytes();
            DatagramPacket packet = new DatagramPacket(sendDataPort, sendDataPort.length, InetAddress.getByName("localhost"), 8888);
            datagramSocket.send(packet);
            datagramSocket.close();
            ConcurrentLinkedQueue<QueueElement> taskQueue = new ConcurrentLinkedQueue<>();
            int i;
            for (i = 0; i <= numbers.size() - STEP; i+=STEP) {
                taskQueue.add(new QueueElement(new ArrayList<>(numbers.subList(i, i+STEP))));
            }
            if (numbers.size()%5 != 0) {
                taskQueue.add(new QueueElement(new ArrayList<>(numbers.subList(numbers.size() - numbers.size()%5, numbers.size()))));

            }
            while (true) {
                Socket acceptSocket = serverSocket.accept();
                threads.add(new Planer(taskQueue, serverSocket, acceptSocket));
            }
        } catch (IOException e) {
            serverSocket.close();
            for (Planer thread : threads) {
                if (!thread.getResult()) {
                    System.out.println("Массив содержит не простое число");
                    return;
                }
            }
            System.out.println("Массив простой");
        }
    }
}
