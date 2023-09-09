package ru.nsu.Yukhnina;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    /**
     * Простейший тест
     */
    @Test
    void test1() {
        Main ob = new Main();
        int test_1[] = {2, 1, 5, 6, 4, 3};
        int result_1[] = {1, 2, 3, 4, 5, 6};
        int n = test_1.length;
        assertArrayEquals(result_1, ob.sort(test_1, n));
    }

    /**
     * Тест с отрицательными элементами
     */
    @Test
    void test2() {
        Main ob = new Main();
        int test_2[] = {-2, -1, 5, 6, 4, 3};
        int result_2[] = {-2, -1, 3, 4, 5, 6};
        int n = test_2.length;
        assertArrayEquals(result_2, ob.sort(test_2, n));
    }



}