package ru.nsu.yukhnina.snake.models;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Описание ячейки тельца змейки.
 */
public class SnakeCell {
    private int x;
    private int y;
    private Color color;
    int width;
    int height;

    /**
     * Конструктор класса.
     */
    public SnakeCell(int x, int y, Color color, int width, int height) {
        this.x = x % width;
        this.y = y % height;
        this.color = color;
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
     * Геттер для координаты x.
     */
    public int getX() {
        return x;
    }

    /**
     * Геттер для координаты y.
     */
    public int getY() {
        return y;
    }

    /**
     * Геттер для цвета.
     */
    public Paint getColor() {
        return color;
    }

}
