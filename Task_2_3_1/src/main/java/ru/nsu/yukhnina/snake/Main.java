package ru.nsu.yukhnina.snake;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Запускает окно приложения.
 */
public class Main extends Application {

    /**
     * Запускает окно приложения.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/game.fxml"));
        primaryStage.setTitle("Snake");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/logo.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Зачем-то оно нужно.
     */
    public static void main(String[] args) {
        launch(args);
    }
}