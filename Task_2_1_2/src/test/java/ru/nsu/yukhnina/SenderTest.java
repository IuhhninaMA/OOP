package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SenderTest {

    @Test
    void check() {
        Receiver r = new Receiver(12345, "230.0.0.0");
//        Sender s = new Sender(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 55, 8889898, 3, 2, 1, 3, 4, 1)), 8888, 12345);
//        System.out.println(s.isArrayPrime());
    }
}