package ru.nsu.yukhnina;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class MainClient {
    int port;
    ArrayList<Integer> numbers;
    public MainClient(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);

            byte[] sendData = "Hello, world!".getBytes();
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), 12345);

            socket.send(packet);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

