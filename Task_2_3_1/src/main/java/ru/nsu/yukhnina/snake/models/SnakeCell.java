package ru.nsu.yukhnina.snake.models;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class SnakeCell {
    private int x;
    private int y;
    private Color color;
    int width;
    int height;

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x % width;
        if (x < 0) {
            this.x = width;
        }
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
