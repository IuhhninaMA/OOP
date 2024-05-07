package ru.nsu.yukhnina.snake.models;

import javafx.scene.paint.Color;

/**
 * Класс описывающий еду.
 * Наверное и спавн еды было бы разумно сюда добавить,
 * но мне кажется онтслишком тесно связан с game.
 */
public class Food {
    private final Coordinates coordinates;
    private final Color color;

    /**
     * Еда спавнится и сюда сохраняется цвет еды и координаяты.
     */
    public Food(Coordinates coordinates, Color color) {
        this.coordinates = coordinates;
        this.color = color;
    }

    /**
     * Геттер для координат.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Геттер для цвета.
     * Базово он красный,
     * но для изменяемости расширяемости пусть будет так.
     */
    public Color getColor() {
        return color;
    }

}