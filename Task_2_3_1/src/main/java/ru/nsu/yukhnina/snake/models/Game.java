package ru.nsu.yukhnina.snake.models;

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

    /**
     * тут собраны тело змейки,
     * еда,
     * направление для начала игры,
     * размеры ячейки и поля.
     */
    public Game(SnakeBody snake,
                Food food,
                Direction.MyDirection direction,
                int cellSize,
                double fieldHeight,
                double fieldWidth) {
        this.snake = snake;
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
                (int)fieldHeight),
                Color.RED);
    }

    /**
     * Проверка съела ли змейка еду.
     */
    public void eatFood() {
        if (snake.getBody().getFirst().getxCoordinate() == foodX
                && snake.getBody().getFirst().getyCoordinate() == foodY) {
            spawnApple();
            snake.addBody();
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

}
