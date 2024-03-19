package ru.nsu.yukhnina;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.ArrayList;

public class BroadcastReciver2 {
    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(8888);
            byte[] receiveData = new byte[1024];
            //общага 192.168.1.255
//            DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length, InetAddress.getByName("10.9.63.255"), 8888);
            DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length, InetAddress.getByName("230.0.0.1"), 8888);
            datagramSocket.receive(packet);

            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("PortId: " + message);

            Socket socket = new Socket(InetAddress.getByName("230.0.0.1"), Integer.parseInt(message));
            while (true) {
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream in = new ObjectInputStream(inputStream);
                int start = (int) in.readObject();
                int end = (int) in.readObject();
                ArrayList<Integer> numbers = (ArrayList<Integer>) in.readObject();
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(outputStream);
                out.writeObject(new BroadcastReciver2().check(start, end, numbers));
                System.out.println("я посчитал");
            }
            //socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean check(int start, int end, ArrayList<Integer> numbers) {
        return true;
    }
}
