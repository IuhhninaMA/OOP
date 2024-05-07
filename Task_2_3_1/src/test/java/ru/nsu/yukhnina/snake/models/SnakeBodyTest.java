package ru.nsu.yukhnina.snake.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeBodyTest {
    @Test
    void test1() {
        //проверка того, что при использовании newsnake создаётся маленькая змейка
        SnakeBody snake = new SnakeBody(Direction.MyDirection.UP, 10, 200, 200);
        snake.newSnake();
        Assertions.assertTrue(snake.getBody().size() == 1);
    }

    @Test
    void test2() {
        //проверка того, что при использовании newsnake создаётся маленькая змейка
        SnakeBody snake = new SnakeBody(Direction.MyDirection.DOWN, 10, 200, 200);
        snake.newSnake();
        int positionX = snake.getBody().getFirst().getX();
        int positionY = snake.getBody().getFirst().getY();
        snake.moveSnake(Direction.MyDirection.DOWN);
        int newPositionY = snake.getBody().getFirst().getY();
        int newPositionX = snake.getBody().getFirst().getX();
        Assertions.assertTrue(newPositionY - positionY == 10);
        Assertions.assertTrue(newPositionX == positionX);
        snake.moveSnake(Direction.MyDirection.RIGHT);
        newPositionX = snake.getBody().getFirst().getX();
        Assertions.assertTrue(newPositionX - positionX == 10);
    }

    @Test
    void test3() {
        //проверка самоуничтожения
        SnakeBody snake = new SnakeBody(Direction.MyDirection.UP, 10, 200, 200);
        snake.newSnake();
        snake.addBody();
        snake.addBody();
        snake.addBody();
        snake.addBody();
        snake.moveSnake(Direction.MyDirection.UP);
        snake.moveSnake(Direction.MyDirection.RIGHT);
        snake.moveSnake(Direction.MyDirection.DOWN);
        Assertions.assertTrue(snake.SnakeHitItself());
    }
}