package ru.nsu.yukhnina;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MainTest {
    /**Простейший тест.
    * u8h87
     * */
    @Test
    void EasyTest() {
        Main ob = new Main();
        int test1[] = {2, 1, 5, 6, 4, 3};
        int result1[] = {1, 2, 3, 4, 5, 6};
        assertArrayEquals(result1, ob.sort(test1));
    }
    /**Тест с отрицательными элементами.*/
    @Test
    void WithNegativeNumbersTest() {
        Main ob = new Main();
        int test2[] = {-2, -1, 5, 6, 4, 3};
        int result2[] = {-2, -1, 3, 4, 5, 6};
        assertArrayEquals(result2, ob.sort(test2));
    }
    /**
     * Тест с пустым массивом
     */
    @Test
    void EmptyArrayTest() {
        Main ob = new Main();
        int test3[] = {};
        int result3[] = {};
        assertArrayEquals(result3, ob.sort(test3));
    }
    @Test
    void EvenNumOfElemTest() {
        Main ob = new Main();
        int test4[] = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 100, 200, 132};
        int result4[] = {Integer.MIN_VALUE, 0, 100, 132, 200, Integer.MAX_VALUE};
        assertArrayEquals(result4, ob.sort(test4));
    }
    @Test
    void OddNumOfElemTest() {
        Main ob = new Main();
        int test5[] = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 100, 132};
        int result5[] = {Integer.MIN_VALUE, 0, 100, 132, Integer.MAX_VALUE};
        assertArrayEquals(result5, ob.sort(test5));
    }
    @Test
    void OneElem() {
        Main ob = new Main();
        int test6[] = {Integer.MAX_VALUE};
        int result6[] = {Integer.MAX_VALUE};
        assertArrayEquals(result6, ob.sort(test6));
    }
}