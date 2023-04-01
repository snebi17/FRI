module lab.lab.lab2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens lab.lab.lab2 to javafx.fxml;
    exports lab.lab.lab2;
}