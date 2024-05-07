package ru.nsu.yukhnina.snake.models;

import javafx.scene.paint.Color;

import java.util.Random;

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

    public void updateGame(Direction.MyDirection direction) {
        this.direction = direction;
        eatFood();
        snake.moveSnake(direction);
    }

    public void spawnApple() {
        int positionX = random.nextInt((int)fieldWidth/cellSize)*cellSize;
        foodX = positionX;
        int positionY = random.nextInt((int)fieldHeight/cellSize)*cellSize;
        foodY = positionY;
        food = new Food(new Coordinates(positionX, positionY, cellSize, (int)fieldWidth, (int)fieldHeight), Color.RED);
//        do {
//            spawnApple();
//            System.out.println("Нровое яблоко");
//        } while (isFoodInsideSnake(positionX, positionY));
//        System.out.println();
    }
//
//    public boolean isFoodInsideSnake(int x, int y) {
//        for (SnakeCell c : snake.getBody()) {
//            if (c.getX() == x && c.getY() == y) {
//                return true;
//            }
//        }
//        return false;
//    }

    public void eatFood(){
        if(snake.getBody().getFirst().getX() == foodX && snake.getBody().getFirst().getY() == foodY){
            spawnApple();
            snake.addBody();
        }
    }

    public Food getFood() {
        return food;
    }

    public SnakeBody getSnake() {
        return snake;
    }

}
