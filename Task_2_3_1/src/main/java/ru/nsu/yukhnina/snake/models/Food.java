package ru.nsu.yukhnina.snake.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Food {
    private final Coordinates coordinates;
    private final Color color;
    public Food(Coordinates coordinates, Color color) {
        this.coordinates = coordinates;
        this.color = color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Color getColor() {
        return color;
    }

}