package ru.nsu.yukhnina.snake.models;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Описание ячейки тельца змейки.
 */
public class SnakeCell {
    private int coordinateX;
    private int coordinateY;
    private Color color;
    int width;
    int height;

    /**
     * Конструктор класса.
     */
    public SnakeCell(int x, int y, Color color, int width, int height) {
        this.coordinateX = x % width;
        this.coordinateY = y % height;
        this.color = color;
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
     * Геттер для координаты x.
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * Геттер для координаты y.
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     * Геттер для цвета.
     */
    public Paint getColor() {
        return color;
    }

}
