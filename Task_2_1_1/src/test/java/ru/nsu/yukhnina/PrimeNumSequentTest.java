package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PrimeNumSequentTest {
    @Test
    void easyTest() {
        ArrayList<Integer> testList = new ArrayList<>();
        testList.add(7);
        testList.add(2);
        testList.add(3);
        testList.add(5);
        PrimeNumSequent test = new PrimeNumSequent(testList);
        assertFalse(test.check());
    }

    @Test
    void easyTest2() {
        ArrayList<Integer> testList = new ArrayList<>();
        testList.add(2);
        testList.add(3);
        testList.add(5);
        testList.add(8);
        PrimeNumSequent test = new PrimeNumSequent(testList);
        assertTrue(test.check());
    }

    @Test
    void easyTest3() {
        ArrayList<Integer> testList = new ArrayList<>();
        testList.add(2);
        testList.add(3);
        testList.add(5);
        testList.add(10);
        PrimeNumSequent test = new PrimeNumSequent(testList);
        assertTrue(test.check());
    }

    @Test
    void testFromTask() {
        //[6, 8, 7, 13, 5, 9, 4]
        ArrayList<Integer> testList = new ArrayList<>();
        testList.add(6);
        testList.add(8);
        testList.add(7);
        testList.add(13);
        testList.add(5);
        testList.add(9);
        testList.add(4);
        PrimeNumSequent test = new PrimeNumSequent(testList);
        assertTrue(test.check());
    }

    @Test
    void testFromTask2() {
        //[20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
        //6998009, 6998029, 6998039, 20165149, 6998051, 6998053]
        ArrayList<Integer> testList = new ArrayList<>();
        testList.add(20319251);
        testList.add(6997901);
        testList.add(6997927);
        testList.add(6997937);
        testList.add(17858849);
        testList.add(6997967);
        testList.add(6998009);
        testList.add(6998029);
        testList.add(6998039);
        testList.add(20165149);
        testList.add(6998051);
        testList.add(6998053);
        PrimeNumSequent test = new PrimeNumSequent(testList);
        assertFalse(test.check());
    }
}