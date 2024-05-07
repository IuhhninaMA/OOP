package ru.nsu.yukhnina.snake.models;

/**
 * Класс описывающий кольцо координат
 * в пространстве игрового поля.
 */
public class Coordinates {
    private int x;
    private int y;
    private int cellSize;
    int width;
    int height;

    /**
     * X - координата по x,
     * Y - координата по y,
     * cellSize - размер ячейки на экране,
     * widthб height - ширина высота поля.
     */
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

    /**
     * Getter x.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter y.
     */
    public int getY() {
        return y;
    }
}
