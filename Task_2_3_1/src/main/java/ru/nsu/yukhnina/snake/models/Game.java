package ru.nsu.yukhnina.snake.models;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;



/**
 * класс который управляет обновлением механики игры.
 */
public class Game {
    private final Random random = new Random();
    SnakeBody snake;
    Food food;
    int foodX;
    int foodY;
    Direction.MyDirection direction;
    int cellSize;
    double fieldWidth;
    double fieldHeight;
    ArrayList<BadSnake> badSnake;

    /**
     * тут собраны тело змейки,
     * еда,
     * направление для начала игры,
     * размеры ячейки и поля.
     */
    public Game(SnakeBody snake,
                ArrayList<BadSnake> badSnake,
                Food food,
                Direction.MyDirection direction,
                int cellSize,
                double fieldHeight,
                double fieldWidth) {
        this.snake = snake;
        this.badSnake = badSnake;
        this.food = food;
        this.direction = direction;
        this.cellSize = cellSize;
        this.fieldHeight = fieldHeight;
        this.fieldWidth = fieldWidth;
    }

    /**
     * запускается с каждым тиком игры для обновления моделей змейки и еды.
     */
    public void updateGame(Direction.MyDirection direction) {
        this.direction = direction;
        eatFood();
        snake.moveSnake(direction);
        badSnakeEatFood();
        badSnake.get(0).snakeGo(foodX, foodY);
        badSnake.get(1).snakeGo(snake.getBody().getFirst().getCoordinateX(),
                snake.getBody().getFirst().getCoordinateX());
    }

    /**
     * рандомный спавн яблок.
     */
    public void spawnApple() {
        int positionX = random.nextInt((int) fieldWidth / cellSize) * cellSize;
        foodX = positionX;
        int positionY = random.nextInt((int) fieldHeight / cellSize) * cellSize;
        foodY = positionY;
        food = new Food(new Coordinates(
                positionX,
                positionY,
                cellSize,
                (int) fieldWidth,
                (int) fieldHeight),
                Color.RED);
    }

    /**
     * Проверка съела ли змейка еду.
     */
    public void eatFood() {
        if (snake.getBody().getFirst().getCoordinateX() == foodX
                && snake.getBody().getFirst().getCoordinateY() == foodY) {
            spawnApple();
            snake.addBody();
        }
    }

    public void badSnakeEatFood() {
        for (BadSnake badsnake : badSnake) {
            if (badsnake.getBody().getFirst().getCoordinateX() == foodX
                    && badsnake.getBody().getFirst().getCoordinateY() == foodY) {
                spawnApple();
                badsnake.addBody();
            }
        }
    }

    /**
     * Геттер для визуализации.
     */
    public Food getFood() {
        return food;
    }

    /**
     * Геттер для визуализации.
     */
    public SnakeBody getSnake() {
        return snake;
    }

    public ArrayList<BadSnake> getBadSnake() {
        return badSnake;
    }

    public boolean SnakesHits() {
        for (BadSnake badSnake : badSnake) {
            for (SnakeCell badCell : badSnake.getBody()) {
                for (SnakeCell snakeCell : snake.getBody()) {
                    if (snakeCell.getCoordinateX() == badCell.getCoordinateX() &&
                            snakeCell.getCoordinateY() == badCell.getCoordinateY()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
