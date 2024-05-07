package ru.nsu.yukhnina.snake.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//буду тестировать,
// что при любых возможных поведениях змейки,
// она останется внутри поля
class CoordinatesTest {

    @Test
    void test1() {
        Coordinates c = new Coordinates(-300, 900, 0, 200, 300);
        Assertions.assertTrue(c.getxCoordinate() >= 0);
        Assertions.assertTrue(c.getyCoordinate() <= 300);
    }

    @Test
    void test2() {
        Coordinates c = new Coordinates(1000, 10, 0, 200, 300);
        Assertions.assertTrue(c.getxCoordinate() == 0);
        Assertions.assertTrue(c.getyCoordinate() == 10);
    }

    @Test
    void test3() {
        Coordinates c = new Coordinates(10, 10, 0, 200, 300);
        Assertions.assertTrue(c.getxCoordinate() == 10);
        Assertions.assertTrue(c.getyCoordinate() == 10);
    }
}