package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CounterTest {
    @Test
    void emptyArray() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread.start();
        Thread.sleep(3000);
        Sender s = new Sender(new ArrayList<>(), 8888, 1234);
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
        Sender s = new Sender(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 55, 8889898, 3, 2, 1, 3, 4, 1)), 8888, 1234);
        thread.join();
        assertFalse(s.isArrayPrime());
    }

    @Test
    void primeArray() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        Thread thread2 = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread.start();
        thread2.start();
        Thread.sleep(3000);
        Sender s = new Sender(new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2)), 8888, 1234);
        thread.join();
        thread2.join();
        assertTrue(s.isArrayPrime());
    }

    @Test
    void bigPrimeArray() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        Thread thread2 = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        Thread thread3 = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        Thread thread4 = new Thread(() -> {
            Receiver r = new Receiver(12345, "230.0.0.0");
        });
        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
        Thread.sleep(3000);
        ArrayList<Integer> testArray = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            testArray.add(i);
        }
        Sender s = new Sender(testArray, 8888, 1234);
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

    @Test
    void counterTestTrue() {
        Counter c = new Counter(new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2)));
        assertTrue(c.countPrime());
    }

    @Test
    void counterTestFasle() {
        Counter c = new Counter(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        assertFalse(c.countPrime());
    }
}