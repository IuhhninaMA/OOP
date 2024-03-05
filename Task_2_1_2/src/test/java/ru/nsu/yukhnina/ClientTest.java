package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void test1 () {
        ArrayList<Integer> k = new ArrayList<>();
        k.add(1);
        k.add(4);
        Client c = new Client(k, 1);
        assertFalse(c.check());
    }

    @Test
    void test2 () {
        ArrayList<Integer> k = new ArrayList<>();
        k.add(1);
        k.add(3);
        k.add(5);
        k.add(7);
        Client c = new Client(k, 1);
        assertTrue(c.check());
    }

}