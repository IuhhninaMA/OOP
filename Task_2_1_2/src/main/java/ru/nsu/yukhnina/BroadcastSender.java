package ru.nsu.yukhnina;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;

public class BroadcastSender {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(12347, 50, InetAddress.getLocalHost());
            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);

            byte[] sendDataPort = "12347".getBytes();
            DatagramPacket packet = new DatagramPacket(sendDataPort, sendDataPort.length, InetAddress.getLocalHost(), 8888);

            socket.send(packet);

            serverSocket.accept();
            serverSocket.accept();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
