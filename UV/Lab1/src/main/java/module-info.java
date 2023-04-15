module lab1.lab1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens lab1.lab1 to javafx.fxml;
    exports lab1.lab1;
}