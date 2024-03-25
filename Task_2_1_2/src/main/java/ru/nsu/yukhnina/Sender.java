package ru.nsu.yukhnina;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;


@SuppressWarnings("ALL")
public class Sender {
    private ArrayList<Integer> numbers;
    private int SERVER_SOCKET_PORT;
    private int DATAGRAMM_SOCKET_PORT;
    private boolean isPrime;
    public Sender(ArrayList<Integer> numbers, int SERVER_SOCKET_PORT, int DATAGRAMM_SOCKET_PORT) {
        this.numbers = numbers;
        this.SERVER_SOCKET_PORT = SERVER_SOCKET_PORT;
        this.DATAGRAMM_SOCKET_PORT = DATAGRAMM_SOCKET_PORT;
        startWorkSender();
    }
    public boolean isArrayPrime() {
        return isPrime;
    }

    public void startWorkSender() {
        final int STEP = 5;
        final ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 3, 5, 7, 8));
        ServerSocket serverSocket = null;
        ArrayList<Planer> threads = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(SERVER_SOCKET_PORT, 50, InetAddress.getByName("localhost"));
            DatagramSocket datagramSocket = new DatagramSocket(DATAGRAMM_SOCKET_PORT);
            byte[] sendDataPort = "8888".getBytes();
            DatagramPacket packet = new DatagramPacket(sendDataPort, sendDataPort.length, InetAddress.getByName("230.0.0.0"), 12345);
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
            try {
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException ex) {
                System.out.println();
            }
            for (Planer thread : threads) {
                if (!thread.getResult()) {
                    boolean isPrime = false;
                    return;
                }
            }
            System.out.println("Массив простой");
            isPrime = true;
        }
    }
}
