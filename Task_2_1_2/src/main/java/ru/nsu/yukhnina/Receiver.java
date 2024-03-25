package ru.nsu.yukhnina;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.*;
import java.util.ArrayList;


@SuppressWarnings("ALL")
public class Receiver {
    private final int port;
    private final String host;

    public Receiver(int port, String host) {
        this.port = port;
        this.host = host;
        startWork();
    }

        public void startWork(){

        Socket socket = null;
        try (MulticastSocket datagrammSocket = new MulticastSocket(port)) {
            NetworkInterface netIf = NetworkInterface.getByName("bge0");
            datagrammSocket.joinGroup(new InetSocketAddress(InetAddress.getByName(host), 0), netIf);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            datagrammSocket.receive(receivePacket);

            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            socket = new Socket(InetAddress.getByName("localhost"), Integer.parseInt(receivedMessage));
            while (true) {

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String message = in.readLine();
                if (message == null) {
                    socket.close();
                    break;
                }
                ObjectMapper objectMapper = new ObjectMapper();
                ArrayList<Integer> numbers = objectMapper.readValue(message, ArrayList.class);
                Boolean result = new Counter(numbers).countPrime();
                out.write(objectMapper.writeValueAsString(result) +"\n");
                out.flush();

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
