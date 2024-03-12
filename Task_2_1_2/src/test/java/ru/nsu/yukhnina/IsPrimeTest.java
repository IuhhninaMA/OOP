package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IsPrimeTest {

    ArrayList<Integer> testList =
            new ArrayList<>(Arrays.asList(7, 2, 3, 5));

    ArrayList<Integer> testList2 =
            new ArrayList<>(Arrays.asList(7, 2, 3, 5, 8));

    ArrayList<Integer> testList3 =
            new ArrayList<>(Arrays.asList(7, 2, 3, 5, 8, 16, 30, 6, 7,
                    9, 10, 15, 100, 777, 90));
    @Test
    void check() {
        try {
            IsPrime test = new IsPrime(testList, 2);
            assertTrue(test.check());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void check2() throws InterruptedException {
        IsPrime test = new IsPrime(testList3, 2);
        assertFalse(test.check());
    }

}