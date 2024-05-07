package ru.nsu.yukhnina.snake.models;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Описание ячейки тельца змейки.
 */
public class SnakeCell {
    private int xCoordinate;
    private int yCoordinate;
    private Color color;
    int width;
    int height;

    /**
     * Конструктор класса.
     */
    public SnakeCell(int x, int y, Color color, int width, int height) {
        this.xCoordinate = x % width;
        this.yCoordinate = y % height;
        this.color = color;
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
     * Геттер для координаты x.
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /**
     * Геттер для координаты y.
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * Геттер для цвета.
     */
    public Paint getColor() {
        return color;
    }

}
