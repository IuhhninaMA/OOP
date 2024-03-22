package ru.nsu.yukhnina;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Reciver {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try {
            DatagramSocket datagramSocket = new DatagramSocket(8888);
            byte[] receiveData = new byte[1024];
            DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length, InetAddress.getByName("localhost"), 8888);
            datagramSocket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("PortId: " + message);
            socket = new Socket(InetAddress.getByName("localhost"), Integer.parseInt(message));
            while (true) {
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream in = new ObjectInputStream(inputStream);
                ArrayList<Integer> numbers = (ArrayList<Integer>) in.readObject();
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(outputStream);
                out.writeObject(countPrime(numbers));
                System.out.println("я посчитал");
            }
        } catch (SocketException e) {
            System.out.println("Не могу создать датаграмм сокет");
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            socket.close();
        } catch (IOException e) {
            socket.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Ойб ошибочка вышла");
            throw new RuntimeException(e);
        }
    }

    private static boolean countPrime(ArrayList<Integer> numbers) {
        for (Integer i : numbers) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}