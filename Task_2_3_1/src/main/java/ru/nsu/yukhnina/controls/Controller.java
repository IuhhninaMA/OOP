package ru.nsu.yukhnina.controls;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ru.nsu.yukhnina.Food;
import ru.nsu.yukhnina.Position;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button startButton;

    private final Double snakeSize = 50.;
    private Rectangle snakeHead;
    private Rectangle snakeTail_1;
    double xPos;
    double yPos;
    Food food;
    private Direction direction;
    public enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }
    private final List<Position> positions = new ArrayList<>();
    private final ArrayList<Rectangle> snakeBody = new ArrayList<>();
    private int gameTicks;
    Timeline timeline;


    @FXML
    void start() {
        for (Rectangle snake : snakeBody) {
            anchorPane.getChildren().remove(snake);
        }
        gameTicks = 0;
        positions.clear();
        snakeBody.clear();
        snakeHead = new Rectangle(250, 250, snakeSize, snakeSize);
        snakeTail_1 = new Rectangle(snakeHead.getX() - snakeSize, snakeHead.getY(), snakeSize, snakeSize);
        xPos = snakeHead.getLayoutX();
        yPos = snakeHead.getLayoutY();
        direction = Direction.RIGHT;
        food.moveFood();
        snakeBody.add(snakeHead);
        snakeHead.setFill(Color.RED);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        snakeBody.add(snakeTail_1);
        anchorPane.getChildren().addAll(snakeHead, snakeTail_1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), e -> {
            positions.add(new Position(snakeHead.getX() + xPos, snakeHead.getY() + yPos, 600, 600));
            moveSnakeHead(snakeHead);
            for (int i = 1; i < snakeBody.size(); i++) {
                moveSnakeTail(snakeBody.get(i), i);
            }
            eatFood();
            gameTicks++;
            if(snakeHitItSelf()){
                timeline.stop();
            }
        }));
        food = new Food(-50,-50,anchorPane,snakeSize);
    }

    @FXML
    void moveSquareKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                direction = Direction.UP;
                break;
            case DOWN:
                direction = Direction.DOWN;
                break;
            case RIGHT:
                direction = Direction.RIGHT;
                break;
            case LEFT:
                direction = Direction.LEFT;
                break;
        }
    }

    private void moveSnakeHead(Rectangle snakeHead) {
        if (direction.equals(Direction.RIGHT)) {
            xPos = (xPos + snakeSize) % 600;
            snakeHead.setTranslateX(xPos);
        } else if (direction.equals(Direction.LEFT)) {
            xPos =( xPos - snakeSize) % 600;
            snakeHead.setTranslateX(xPos);
        } else if (direction.equals(Direction.UP)) {
            yPos = (yPos - snakeSize) % 600;
            snakeHead.setTranslateY(yPos);
        } else if (direction.equals(Direction.DOWN)) {
            yPos = (yPos + snakeSize) % 600;
            snakeHead.setTranslateY(yPos);
        }
    }

    private void moveSnakeTail(Rectangle snakeTail, int tailNumber) {
        double yPos = positions.get(gameTicks - tailNumber + 1).getYPos() - snakeTail.getY();
        double xPos = positions.get(gameTicks - tailNumber + 1).getXPos() - snakeTail.getX();
        snakeTail.setTranslateX(xPos);
        snakeTail.setTranslateY(yPos);
    }

    private void addSnakeTail() {
        Rectangle snakeTail = new Rectangle(
                snakeBody.get(1).getX() + xPos + snakeSize,
                snakeBody.get(1).getY() + yPos,
                snakeSize, snakeSize);
        snakeBody.add(snakeTail);
        anchorPane.getChildren().add(snakeTail);
    }


    public boolean snakeHitItSelf(){
        int size = positions.size() - 1;
        if(size > 2){
            for (int i = size - snakeBody.size(); i < size; i++) {
                if(positions.get(size).getXPos() == (positions.get(i).getXPos())
                        && positions.get(size).getYPos() == (positions.get(i).getYPos())){
                    return true;
                }
            }
        }
        return false;
    }

    private void eatFood(){
        if(xPos + snakeHead.getX() == food.getPosition().getXPos() && yPos + snakeHead.getY() == food.getPosition().getYPos()){
            foodCantSpawnInsideSnake();
            addSnakeTail();
        }
    }

    private boolean isFoodInsideSnake(){
        int size = positions.size();
        if(size > 2){
            for (int i = size - snakeBody.size(); i < size; i++) {
                if(food.getPosition().getXPos() == (positions.get(i).getXPos())
                        && food.getPosition().getYPos() == (positions.get(i).getYPos())){
                    return true;
                }
            }
        }
        return false;
    }

    private void foodCantSpawnInsideSnake(){
        do {
            food.moveFood();
        } while (isFoodInsideSnake());
    }

}