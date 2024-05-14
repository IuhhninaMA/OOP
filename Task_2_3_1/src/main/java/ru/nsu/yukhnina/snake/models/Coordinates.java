package ru.nsu.yukhnina.snake.models;

/**
 * Класс описывающий кольцо координат
 * в пространстве игрового поля.
 */
public class Coordinates {
    private int coordinateX;
    private int coordinateY;
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
        this.coordinateX = x % width;
        this.coordinateY = y % height;
        this.cellSize = cellSize;
        this.width = width;
        this.height = height;
        if (x < 0) {
            this.coordinateX = width;
        }
        if (y < 0) {
            this.coordinateY = height;
        }
    }

    /**
     * Getter x.
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * Getter y.
     */
    public int getCoordinateY() {
        return coordinateY;
    }
}
