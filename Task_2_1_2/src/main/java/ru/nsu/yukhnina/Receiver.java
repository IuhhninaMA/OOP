package ru.nsu.yukhnina;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Receiver {
    private final int port;
    private final String host;

    public Receiver(int port, String host) {
        this.port = port;
        this.host = host;
        startWork();
    }

    //    public static void main(String[] args) {
    public void startWork() {
//        int port = 12345;
//        String host = "230.0.0.0";
        Socket socket = null;
        try (MulticastSocket datagrammSocket = new MulticastSocket(port)) {
            NetworkInterface netIf = NetworkInterface.getByName("bge0");
            datagrammSocket.joinGroup(new InetSocketAddress(InetAddress.getByName("228.5.6.7"), 0), netIf);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            datagrammSocket.receive(receivePacket);

            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received message: " + receivedMessage);
            socket = new Socket(InetAddress.getByName("localhost"), Integer.parseInt(receivedMessage));
            while (true) {
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream in = new ObjectInputStream(inputStream);
                Object numbers = in.readObject();
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(outputStream);
                out.writeObject(new Counter(numbers).countPrime());
                System.out.println("я посчитал");
            }
        } catch (SocketException e) {
            System.out.println("Не могу создать датаграмм сокет");
        } catch (IOException e) {
            try {
                assert socket != null;
                socket.close();
            } catch (IOException ex) {
                System.out.println();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Не могу прочитать массив");
        }
    }
}
