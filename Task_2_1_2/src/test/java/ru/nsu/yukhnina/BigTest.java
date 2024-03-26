package ru.nsu.yukhnina;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;




class BigTest {
    @Test
    void emptyArray() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread.start();
        Thread.sleep(3000);
        Sender s = new Sender(new ArrayList<>(),
                8888,
                1234,
                12345,
                "230.0.0.0");
        thread.join();
        assertTrue(s.isArrayPrime());
    }

    @Test
    void notPrimeArray() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread.start();
        Thread.sleep(3000);
        Sender s = new Sender(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 55, 8889898, 3, 2, 1, 3, 4, 1)),
                8888,
                1234,
                12345,
                "230.0.0.0");
        thread.join();
        assertFalse(s.isArrayPrime());
    }

    @Test
    void primeArray() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread.start();
        Thread thread2 = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread2.start();
        Thread.sleep(3000);
        Sender s = new Sender(new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2)),
                8888,
                1234,
                12345,
                "230.0.0.0");
        thread.join();
        thread2.join();
        assertTrue(s.isArrayPrime());
    }

    @Test
    void bigPrimeArray() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread.start();
        Thread thread2 = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread2.start();
        Thread thread3 = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread3.start();
        Thread thread4 = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread4.start();
        Thread.sleep(3000);
        ArrayList<Integer> testArray = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            testArray.add(i);
        }
        Sender s = new Sender(testArray,
                8888,
                1234,
                12345,
                "230.0.0.0");
        thread.join();
        thread2.join();
        thread3.join();
        thread4.join();
        assertFalse(s.isArrayPrime());
    }

    @Test
    void countPrime2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread.start();
        Thread.sleep(3000);
        Sender s = new Sender(new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2)),
                8888,
                1234,
                12345,
                "230.0.0.0");
        thread.join();
        assertTrue(s.isArrayPrime());
    }

    @Test
    void countPrime3() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread.start();
        Thread.sleep(3000);
        Sender s = new Sender(new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2)),
                8888,
                1234,
                12345,
                "230.0.0.0");
        thread.join();
        assertTrue(s.isArrayPrime());
    }

}