package ru.nsu.yukhnina.snake.controls;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ru.nsu.yukhnina.snake.models.Direction;
import ru.nsu.yukhnina.snake.models.Food;
import ru.nsu.yukhnina.snake.models.Game;
import ru.nsu.yukhnina.snake.models.SnakeBody;
import ru.nsu.yukhnina.snake.presentation.ViewGame;

/**
 * Ну контроллер и контроллер...
 * Коннектит управление с визуализацией.
 */
public class Controller {
    public Label score;
    public Button hardLevel;
    public Button mediumLevel;
    public Button setSmallSize;
    public Button setBigSize;
    private Direction.MyDirection direction = Direction.MyDirection.UP;
    public Canvas canvas;
    public Button startButton;
    SnakeBody snake;
    private long lastUpdateTime = 0;
    private long updateInterval = 1_000_000_000;
    Food food;
    int cellSize = 20;
    Game game;

    /**
     * Ловит нажатия кнопок.
     */
    @FXML
    void moveSquareKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                if (direction != Direction.MyDirection.DOWN) {
                    direction = Direction.MyDirection.UP;
                }
                break;
            case DOWN:
                if (direction != Direction.MyDirection.UP) {
                    direction = Direction.MyDirection.DOWN;
                }
                break;
            case RIGHT:
                if (direction != Direction.MyDirection.LEFT) {
                    direction = Direction.MyDirection.RIGHT;
                }
                break;
            case LEFT:
                if (direction != Direction.MyDirection.RIGHT) {
                    direction = Direction.MyDirection.LEFT;
                }
                break;
            default:
                direction = direction;
        }
    }

    /**
     * Подготовка всех моделей для начала игры.
     */
    public void startGame(MouseEvent mouseEvent) {
        snake = new SnakeBody(direction, cellSize,
                (int) canvas.getWidth(), (int) canvas.getHeight());
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
            score.setText("" + (snake.getBody().size() - 1));
            if (now - lastUpdateTime >= updateInterval) {
                lastUpdateTime = now;
                game.updateGame(direction);
                ViewGame paint = new ViewGame(game.getSnake(), game.getFood(), cellSize, canvas);
                paint.view();
                System.out.println(direction);
            }
            if (snake.snakeHitItself()) {
                ViewGame paint = new ViewGame(game.getSnake(), game.getFood(), cellSize, canvas);
                paint.gameOver();
                timer.stop();
            }
        }
    };

    public void mediumLevel() {
        updateInterval = 1_000_000_000 / 2;
    }

    public void hardLevel() {
        updateInterval = 1_000_000_000 / 5;
    }

    public void smallSize(MouseEvent mouseEvent) {
        cellSize = 10;
    }

    public void bigSize(MouseEvent mouseEvent) {
        cellSize = 40;
    }
}