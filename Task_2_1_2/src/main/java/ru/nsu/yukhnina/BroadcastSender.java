package ru.nsu.yukhnina;

import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class BroadcastSender {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<DataSend> sockets = new ArrayList<>();
        int currentIndex = 0;
        int step = 0;
        if (numbers.size() >= 5) {
            step = 5;
        }
        else if (!numbers.isEmpty()){
            step = 1;
        }

        try {
            ServerSocket serverSocket = new ServerSocket(12347, 50, InetAddress.getByName("192.168.1.255"));
            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);
            byte[] sendDataPort = "12347".getBytes();
            //общага 192.168.1.255
//            DatagramPacket packet = new DatagramPacket(sendDataPort, sendDataPort.length, InetAddress.getByName("10.9.55.255"), 8888);
            DatagramPacket packet = new DatagramPacket(sendDataPort, sendDataPort.length, InetAddress.getByName("192.168.1.255"), 8888);

            socket.send(packet);
            socket.close();

            while (true) {
                if (currentIndex == numbers.size()) {
                    break;
                }
                if (currentIndex + step > numbers.size()) {
                    step = numbers.size() - currentIndex;
                }
                for (DataSend thread : sockets) {
                    //проверяю закончились ли вычисления и как закончились успешно или нет
                    if (thread.isFinalCount) {
                        if (!thread.isPrime) {
                            System.out.println("всё пропало, массив не простой");
                        }
                        else {
                            thread.setStart(currentIndex, step);
                        }
                    }
                }
                Socket acceptSocket = serverSocket.accept();
                //Получается косяк когда жду или освободившиеся нити или подключённые, а если ни того ни того
                sockets.add(new DataSend(numbers, acceptSocket, currentIndex, currentIndex+step));
                currentIndex += step;
                System.out.println("кто-то подключился");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
