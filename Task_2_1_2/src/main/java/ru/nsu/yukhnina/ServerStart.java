package ru.nsu.yukhnina;

import java.net.Socket;

/**
 * Нить запускает сервер.
 */
public class ServerStart implements Runnable {

    private int portId;

    /**
     * У каждого сервера уникальный номер порта,
     * соответствующий номеру подключаемого клиента.
     */
    public ServerStart(int portId) {
        this.portId = portId;
    }


    /**
     * Создание нового сервера и запуск.
     */
    @Override
    public void run() {
        ServerCount server = new ServerCount(portId);
        System.out.println("Сервер пашет");
        server.checkIsItPrimeOnServer();

    }
}
