package ru.nsu.yukhnina.snake.models;

import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class BadSnake {
    public ArrayDeque<SnakeCell> body = new ArrayDeque<>();
    private Direction.MyDirection direction;
    private int cellSize;
    int fieldWidth;
    int fieldHeight;
    int goalX;
    int goalY;

    public BadSnake( int cellSize,
                     int fieldWidth,
                     int fieldHeight,
                     int goalX,
                     int goalY) {
        this.fieldHeight = fieldHeight;
        this.fieldWidth = fieldWidth;
        this.direction = Direction.MyDirection.UP;
        this.cellSize = cellSize;
        this.goalX = goalX;
        this.goalY = goalY;
    }

    /**
     * Инициализация новой змейки.
     */
    public void newSnake() {
        body.clear();
        body.add(new SnakeCell(0, 200, Color.YELLOW, fieldWidth, fieldHeight));
    }

    /**
     * Возвращает тельце змейки.
     */
    public ArrayDeque<SnakeCell> getBody() {
        return body;
    }

    public void addBody() {
        SnakeCell c = body.getLast();
        switch (direction) {
            case UP -> body.add(
                    new SnakeCell(
                            c.getCoordinateX(),
                            c.getCoordinateY() - cellSize,
                            Color.YELLOW,
                            fieldWidth,
                            fieldHeight));
            case DOWN -> body.add(
                    new SnakeCell(c.getCoordinateX(),
                            c.getCoordinateY() + cellSize,
                            Color.YELLOW,
                            fieldWidth,
                            fieldHeight));
            case LEFT -> body.add(
                    new SnakeCell(
                            c.getCoordinateX() + cellSize,
                            c.getCoordinateY(),
                            Color.YELLOW,
                            fieldWidth,
                            fieldHeight));
            case RIGHT -> body.add(
                    new SnakeCell(
                            c.getCoordinateX() - cellSize,
                            c.getCoordinateY(),
                            Color.YELLOW,
                            fieldWidth,
                            fieldHeight));
            default -> direction = direction;
        }
    }

    private void moveSnake() {
        switch (direction) {
            case UP ->
                    body.addFirst(
                            new SnakeCell(
                                    body.getFirst().getCoordinateX(),
                                    body.getFirst().getCoordinateY() - cellSize,
                                    Color.YELLOW,
                                    fieldWidth,
                                    fieldHeight));
            case DOWN -> body.addFirst(
                    new SnakeCell(
                            body.getFirst().getCoordinateX(),
                            body.getFirst().getCoordinateY() + cellSize,
                            Color.YELLOW,
                            fieldWidth,
                            fieldHeight));
            case LEFT -> body.addFirst(
                    new SnakeCell(
                            body.getFirst().getCoordinateX() - cellSize,
                            body.getFirst().getCoordinateY(),
                            Color.YELLOW,
                            fieldWidth,
                            fieldHeight));
            case RIGHT -> body.addFirst(
                    new SnakeCell(
                            body.getFirst().getCoordinateX() + cellSize,
                            body.getFirst().getCoordinateY(),
                            Color.YELLOW,
                            fieldWidth,
                            fieldHeight));
            default -> direction = direction;
        }
        body.pollLast();
    }

    private void choiceDirection() {
        this.direction = Direction.MyDirection.UP;
        ArrayList<Direction.MyDirection> directions = new ArrayList<>();
        if (goalX < body.getFirst().getCoordinateX()) {
            directions.add(Direction.MyDirection.LEFT);
        }
        if (goalX > body.getFirst().getCoordinateX()) {
            directions.add(Direction.MyDirection.RIGHT);
        }
        if (goalY < body.getFirst().getCoordinateY()) {
            directions.add(Direction.MyDirection.UP);
        }
        if (goalY > body.getFirst().getCoordinateY()) {
            directions.add(Direction.MyDirection.DOWN);
        }
        Random randomGenerator = new Random();
        int index = (int)(Math.random() * directions.size());
        this.direction = directions.get(index);
    }

    public void snakeGo(int newFoodX, int newFoodY) {
        goalX = newFoodX;
        goalY = newFoodY;
        choiceDirection();
        moveSnake();
        System.out.println("Bad snake ");
    }
}
