package ru.nsu.yukhnina.snake.models;

public class Coordinates {
    private int x;
    private int y;
    private int cellSize;
    int width;
    int height;

    public Coordinates(int x,
                       int y,
                       int cellSize,
                       int width,
                       int height) {
        this.x = x % width;
        this.y = y % height;
        this.cellSize = cellSize;
        this.width = width;
        this.height = height;
        if (x < 0) {
            this.x = width;
        }
        if (y < 0) {
            this.y = height;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
