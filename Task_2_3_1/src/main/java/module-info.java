module ru.nsu.yukhnina {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.nsu.yukhnina to javafx.fxml;
    exports ru.nsu.yukhnina;

    exports ru.nsu.yukhnina.controls;
    opens ru.nsu.yukhnina.controls to javafx.fxml;
}
