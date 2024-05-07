package ru.nsu.yukhnina.snake.models;

import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class SnakeBody {
    public ArrayDeque<SnakeCell> body = new ArrayDeque<>();
    private Direction.MyDirection direction;
    private int cellSize;
    int fieldWidth;
    int fieldHeight;

    public SnakeBody(Direction.MyDirection direction,
                     int cellSize,
                     int fieldWidth,
                     int fieldHeight) {
        this.fieldHeight = fieldHeight;
        this.fieldWidth = fieldWidth;
        this.direction = direction;
        this.cellSize = cellSize;
    }

    public void newSnake() {
        body.clear();
        body.add(new SnakeCell(0, 0, Color.GREEN, fieldWidth, fieldHeight));
    }

    public ArrayDeque<SnakeCell> getBody() {
        return body;
    }

    public void addBody() {
        SnakeCell c = body.getLast();
        switch (direction) {
            case UP -> body.add(new SnakeCell(c.getX(), c.getY()-cellSize, Color.GREEN, fieldWidth, fieldHeight));
            case DOWN -> body.add(new SnakeCell(c.getX(), c.getY()+cellSize, Color.GREEN, fieldWidth, fieldHeight));
            case LEFT -> body.add(new SnakeCell(c.getX()+cellSize, c.getY(), Color.GREEN, fieldWidth, fieldHeight));
            case RIGHT -> body.add(new SnakeCell(c.getX()-cellSize, c.getY(), Color.GREEN, fieldWidth, fieldHeight));
        }
    }

    public void moveSnake(Direction.MyDirection direction) {
        System.out.println(body.size());
        switch (direction) {
            case UP ->
                body.addFirst(new SnakeCell(body.getFirst().getX(),
                        body.getFirst().getY()-cellSize,
                        Color.GREEN, fieldWidth, fieldHeight));
            case DOWN -> body.addFirst(new SnakeCell(body.getFirst().getX(), body.getFirst().getY()+cellSize, Color.GREEN, fieldWidth, fieldHeight));
            case LEFT -> body.addFirst(new SnakeCell(body.getFirst().getX()-cellSize, body.getFirst().getY(), Color.GREEN, fieldWidth, fieldHeight));
            case RIGHT -> body.addFirst(new SnakeCell(body.getFirst().getX()+cellSize,body.getFirst().getY(), Color.GREEN, fieldWidth, fieldHeight ));
        }
        body.pollLast();
    }

    public boolean SnakeHitItself() {
        for (SnakeCell cell1 : body) {
            int k = 0;
            for (SnakeCell cell2 : body) {
                if (cell1.getX() == cell2.getX() && cell1.getY() == cell2.getY()) {
                    k++;
                }
                if (k > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public void redSnake() {
        for (SnakeCell c : body) {
            c.setColor(Color.RED);
        }
    }
}
