package ru.nsu.yukhnina;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class IsPrime {
    ArrayList<Integer> numbers ;
    int serversCount;
    volatile boolean isArrayPrime;
    private Thread[] threads;
    ArrayList<ServerStart> servers;
    ArrayList<ClientStart> clients;
    public IsPrime(ArrayList<Integer> inputNum, int inputServersCount) {
        this.serversCount = inputServersCount;
        this.numbers = inputNum;
        servers = new ArrayList<>();
        threads = new Thread[inputServersCount];
        clients = new ArrayList<>();
    }

    public boolean check() throws InterruptedException {
        int arrayLen = numbers.size() / serversCount;
        int countTail = numbers.size() % serversCount;
        int currStart = 0;
        int minPortId = 10000;

        //запускаю первые кусочки массива длины arrayLen+1.
        for (int i = 0; i < countTail; i++) {
            ServerStart newThread = new ServerStart(minPortId+i);
            ClientStart newThreadC = new ClientStart(minPortId+i, currStart, currStart+arrayLen+1, numbers);
            servers.add(newThread);
            Thread childThread = new Thread(newThread);
            Thread childThreadC = new Thread(newThreadC);
            threads[i] = childThread;
            childThread.start();
            sleep(1000);
            childThreadC.start();
            clients.add(newThreadC);
            currStart += arrayLen + 1;
        }

        for (int i = countTail; i < serversCount; i++) {
            ServerStart newThread = new ServerStart(minPortId+i);
            ClientStart newThreadC = new ClientStart(minPortId+i, currStart, currStart+arrayLen, numbers);
            servers.add(newThread);
            Thread childThread = new Thread(newThread);
            Thread childThreadC = new Thread(newThreadC);
            threads[i] = childThread;
            childThread.start();
            sleep(1000);
            childThreadC.start();
            currStart += arrayLen;
            clients.add(newThreadC);
        }

        for (int i = 0; i < serversCount; i++) {
            threads[i].join();
            if (!clients.get(i).result()) {
                //если нашёлся кусочек, где есть непростое число,
                //то сразу прерываем все остальные процессы.
                for (int j = i; j < serversCount; j++) {
                    threads[i].interrupt();
                }
                return false;
            }
        }
        return true;
    }
}