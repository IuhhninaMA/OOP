package ru.nsu.yukhnina.snake.models;

/**
 * Класс описывающий кольцо координат
 * в пространстве игрового поля.
 */
public class Coordinates {
    private int xCoordinate;
    private int yCoordinate;
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
        this.xCoordinate = x % width;
        this.yCoordinate = y % height;
        this.cellSize = cellSize;
        this.width = width;
        this.height = height;
        if (x < 0) {
            this.xCoordinate = width;
        }
        if (y < 0) {
            this.yCoordinate = height;
        }
    }

    /**
     * Getter x.
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /**
     * Getter y.
     */
    public int getyCoordinate() {
        return yCoordinate;
    }
}
