package ru.nsu.yukhnina.snake.presentation;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Cell;
import javafx.scene.paint.Color;
import ru.nsu.yukhnina.snake.models.Food;
import ru.nsu.yukhnina.snake.models.SnakeBody;
import ru.nsu.yukhnina.snake.models.SnakeCell;

public class ViewGame {
    SnakeBody snakeBody;
    Food food;
    int cellSize;
    Canvas canvas;

    public ViewGame(SnakeBody snake,
                    Food food,
                    int cellSize,
                    Canvas canvas) {
        this.snakeBody = snake;
        this.food = food;
        this.cellSize = cellSize;
        this.canvas = canvas;
    }

    public void view() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BISQUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (SnakeCell cell : snakeBody.getBody()) {
            gc.setFill(cell.getColor());
            gc.fillRect(cell.getX(), cell.getY(), cellSize, cellSize);
            System.out.println("Змейка" + cell.getX() + " " + cell.getY());
        }
        gc.setFill(food.getColor());
        gc.fillRect(food.getCoordinates().getX(), food.getCoordinates().getY(), cellSize, cellSize);
    }

    public void gameOver() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.RED);
    }
}
