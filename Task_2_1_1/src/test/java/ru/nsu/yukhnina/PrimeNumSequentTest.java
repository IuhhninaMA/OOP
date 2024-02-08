package ru.nsu.yukhnina;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;




class PrimeNumSequentTest {
    @Test
    void easyTest() {
        ArrayList<Integer> testList =
                new ArrayList<>(Arrays.asList(7, 2, 3, 5));
        PrimeNumSequent test = new PrimeNumSequent(testList);
        assertFalse(test.check());
    }

    @Test
    void easyTest2() {
        ArrayList<Integer> testList =
                new ArrayList<>(Arrays.asList(2, 3, 5, 8));
        PrimeNumSequent test = new PrimeNumSequent(testList);
        assertTrue(test.check());
    }

    @Test
    void easyTest3() {
        ArrayList<Integer> testList =
                new ArrayList<>(Arrays.asList(2, 3, 5, 10));
        PrimeNumSequent test = new PrimeNumSequent(testList);
        assertTrue(test.check());
    }

    @Test
    void testFromTask() {
        //[6, 8, 7, 13, 5, 9, 4]
        ArrayList<Integer> testList =
                new ArrayList<>(Arrays.asList(6, 8, 7, 13, 5, 9, 4));
        PrimeNumSequent test = new PrimeNumSequent(testList);
        assertTrue(test.check());
    }

    @Test
    void testFromTask2() {
        //[20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
        //6998009, 6998029, 6998039, 20165149, 6998051, 6998053]
        ArrayList<Integer> testList =
                new ArrayList<>(Arrays.asList(20319251, 6997901, 6997927,
                        6997937, 17858849, 6997967,
                        6998009, 6998029, 6998039,
                        20165149, 6998051, 6998053));
        PrimeNumSequent test = new PrimeNumSequent(testList);
        assertFalse(test.check());
    }

    @Test
    void bigNum() {
        ArrayList<Integer> testList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testList.add(2);
        }
        for (int i = 0; i < 9000; i++) {
            testList.add(165523);
        }
        PrimeNumSequent test = new PrimeNumSequent(testList);
        assertFalse(test.check());
    }

    //неравномерные потоки
    @Test
    void bigNum2() {
        ArrayList<Integer> testList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testList.add(2);
        }
        for (int i = 0; i < 9000; i++) {
            testList.add(165523);
        }
        testList.add(32);
        PrimeNumSequent test = new PrimeNumSequent(testList);
        assertTrue(test.check());
    }
}