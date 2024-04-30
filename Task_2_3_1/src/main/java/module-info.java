module hellofx {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens ru.nsu.yukhnina to javafx.fxml;
    exports ru.nsu.yukhnina;

}
