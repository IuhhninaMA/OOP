package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CounterTest {
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