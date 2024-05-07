package ru.nsu.yukhnina.snake.models;


import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void testGame() {
        SnakeBody snake = new SnakeBody(Direction.MyDirection.UP, 10, 200, 200);
        snake.newSnake();
        int positionY = snake.body.getFirst().getY();
        Food food = new Food(new Coordinates(200, 200, 10, 300, 300), Color.GREEN);
        Game game = new Game(snake, food, Direction.MyDirection.UP, 10, 300, 300);
        game.spawnApple();
        //значение еды изменилось, она рандомно заспавнилась
        Assertions.assertTrue(game.getFood().getCoordinates().getX() != 200);
        game.updateGame(Direction.MyDirection.DOWN);
        Assertions.assertTrue(snake.body.getFirst().getY() - positionY == 10);
    }
}