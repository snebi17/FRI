module labs.lab4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens labs.lab4 to javafx.fxml;
    exports labs.lab4;
}