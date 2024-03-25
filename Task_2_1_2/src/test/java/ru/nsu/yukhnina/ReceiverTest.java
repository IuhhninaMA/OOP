package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ReceiverTest {

    @Test
    void countPrime() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread.start();
        Thread.sleep(3000);
        Sender s = new Sender(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 55, 8889898, 3, 2, 1, 3, 4, 1)), 8888, 1234);
        thread.join();
        assertFalse(s.isArrayPrime());
    }

    @Test
    void countPrime2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        Thread thread2 = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread.start();
        Thread.sleep(3000);
        Sender s = new Sender(new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2)), 8888, 1234);
        thread.join();
        thread2.join();
        assertTrue(s.isArrayPrime());
    }
}