module uc.unitconverter {
    requires javafx.controls;
    requires javafx.fxml;


    opens uc.unitconverter to javafx.fxml;
    exports uc.unitconverter;
}