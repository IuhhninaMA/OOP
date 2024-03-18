package ru.nsu.yukhnina;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class BroadcastReciver2 {
    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(8888);
            byte[] receiveData = new byte[1024];

            DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
            datagramSocket.receive(packet);

            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("PortId: " + message);
//
//            Socket socket;
//            socket = new Socket(InetAddress.getLocalHost(), Integer.parseInt(message));

//            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
