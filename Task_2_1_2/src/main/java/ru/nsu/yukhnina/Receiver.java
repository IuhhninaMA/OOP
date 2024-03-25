package ru.nsu.yukhnina;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class Receiver {
    private final int port;
    private final String host;

    public Receiver(int port, String host) {
        this.port = port;
        this.host = host;
        startWork();
    }

        public void startWork(){
//    public static void main(String args[]) {
//        int port = 12345;
//        String host = "230.0.0.0";
        Socket socket = null;
        try (MulticastSocket datagrammSocket = new MulticastSocket(port)) {
            NetworkInterface netIf = NetworkInterface.getByName("bge0");
            datagrammSocket.joinGroup(new InetSocketAddress(InetAddress.getByName(host), 0), netIf);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            datagrammSocket.receive(receivePacket);

            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received message: " + receivedMessage);
            socket = new Socket(InetAddress.getByName("localhost"), Integer.parseInt(receivedMessage));
            while (true) {

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String message = in.readLine();
                Gson gson = new Gson();
                var numbers = gson.fromJson(message, ArrayList.class);
                out.write(new Counter(gson.fromJson(message, ArrayList.class)).toString());
                out.flush();
                break;
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
        }
    }
}
