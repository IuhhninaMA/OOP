package ru.nsu.yukhnina;

import java.util.ArrayList;

public class IsPrime {
    ArrayList<Integer> numbers ;
    int serversCount;
    volatile boolean isArrayPrime;
    private Thread[] threads;
    ArrayList<ServerStart> servers;
    public IsPrime(ArrayList<Integer> inputNum, int inputServersCount) {
        this.serversCount = inputServersCount;
        this.numbers = inputNum;
        servers = new ArrayList<>();
        threads = new Thread[inputServersCount];
    }

    public boolean check() throws InterruptedException {
        int arrayLen = numbers.size() / serversCount;
        int countTail = numbers.size() % serversCount;
        int currStart = 0;
        int minPortId = 12349;

        //если количество запрашиваемых серверов больше массива считается всё на одном.
        if (numbers.size() < serversCount) {
            serversCount = 1;
        }
        //запускаю первые кусочки массива длины arrayLen+1.
        for (int i = 0; i < countTail; i++) {
            ServerStart newThread = new ServerStart(minPortId+i, currStart, currStart+arrayLen+1, numbers);
            servers.add(newThread);
            Thread childThread = new Thread(newThread);
            threads[i] = childThread;
            childThread.start();
            currStart += arrayLen + 1;
        }

        for (int i = countTail; i < serversCount; i++) {
            ServerStart newThread = new ServerStart(i, currStart, currStart+arrayLen, numbers);
            servers.add(newThread);
            Thread childThread = new Thread(newThread);
            threads[i] = childThread;
            childThread.start();
            currStart += arrayLen;
        }

        for (int i = 0; i < serversCount; i++) {
            threads[i].join();
            if (!servers.get(i).getResult()) {
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