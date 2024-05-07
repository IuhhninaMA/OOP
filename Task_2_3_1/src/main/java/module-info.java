module ru.nsu.yukhnina.snake {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.nsu.yukhnina.snake to javafx.fxml;
    exports ru.nsu.yukhnina.snake;

    exports ru.nsu.yukhnina.snake.controls;
    opens ru.nsu.yukhnina.snake.controls to javafx.fxml;
    opens ru.nsu.yukhnina.snake.models to javafx.fxml;
    exports ru.nsu.yukhnina.snake.models;
}
