package ru.nsu.yukhnina.snake.models;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SnakeCellTest {

    @Test
    void test1() {
        SnakeCell cell = new SnakeCell(-400, 30, Color.GREEN, 200, 300);
        Assertions.assertTrue(cell.getX() == 200);
        Assertions.assertTrue(cell.getY() == 30);
    }

    @Test
    void test2() {
        SnakeCell cell = new SnakeCell(400, 500, Color.GREEN, 200, 300);
        Assertions.assertTrue(cell.getX() == 0);
        Assertions.assertTrue(cell.getY() == 200);
    }

    @Test
    void test3() {
        SnakeCell cell = new SnakeCell(10, 20, Color.GREEN, 200, 300);
        Assertions.assertTrue(cell.getX() == 10);
        Assertions.assertTrue(cell.getY() == 20);
        Assertions.assertTrue(cell.getColor() == Color.GREEN);
    }
}