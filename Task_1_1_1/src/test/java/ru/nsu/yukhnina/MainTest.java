package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MainTest {
    /**Простейший тест
     */
    @Test
    void test1() {
        Main ob = new Main();
        int test1[] = {2, 1, 5, 6, 4, 3};
        int result1[] = {1, 2, 3, 4, 5, 6};
        int n = test1.length;
        assertArrayEquals(result1, ob.sort(test1, n));
    }
    /**Тест с отрицательными элементами
     */
    @Test
    void test2() {
        Main ob = new Main();
        int test2[] = {-2, -1, 5, 6, 4, 3};
        int result2[] = {-2, -1, 3, 4, 5, 6};
        int n = test2.length;
        assertArrayEquals(result2, ob.sort(test2, n));
    }
}