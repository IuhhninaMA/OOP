package ru.nsu.yukhnina.snake.controls;


import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ru.nsu.yukhnina.snake.models.*;
import ru.nsu.yukhnina.snake.presentation.ViewGame;

public class Controller {
    private Direction.MyDirection direction = Direction.MyDirection.UP;
    public Canvas canvas;
    public Button startButton;
    SnakeBody snake;
    private long lastUpdateTime = 0;
    private long updateInterval = 1_000_000_000;
    Food food;
    int cellSize = 20;
    Game game;

    @FXML
    void moveSquareKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                direction = Direction.MyDirection.UP;
                break;
            case DOWN:
                direction = Direction.MyDirection.DOWN;
                break;
            case RIGHT:
                direction = Direction.MyDirection.RIGHT;
                break;
            case LEFT:
                direction = Direction.MyDirection.LEFT;
                break;
        }
    }

    public void startGame(MouseEvent mouseEvent) {
        snake = new SnakeBody(direction, cellSize, (int)canvas.getWidth(), (int)canvas.getHeight());
        snake.newSnake();
        game = new Game(snake,
                food,
                direction,
                cellSize,
                canvas.getHeight(),
                canvas.getWidth());
        game.spawnApple();
        lastUpdateTime = System.nanoTime();
        timer.start();
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            updateInterval -= 10;
            if (now - lastUpdateTime >= updateInterval) {
                lastUpdateTime = now;
                game.updateGame(direction);
                ViewGame paint = new ViewGame(game.getSnake(), game.getFood(), cellSize, canvas);
                paint.view();
                System.out.println(direction);
            }
            if (snake.SnakeHitItself()) {
                ViewGame paint = new ViewGame(game.getSnake(), game.getFood(), cellSize, canvas);
                paint.gameOver();
                timer.stop();
            }
        }
    };
}