package com.example.lab3;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class HelloController {
    /***
     * Podatki o zavarovancu
     */
    @FXML
    private TextField zavarovanec_Ime;
    @FXML
    private TextField zavarovanec_Priimek;
    @FXML
    private TextField zavarovanec_Ulica;
    @FXML
    private TextField zavarovanec_HSt;
    @FXML
    private TextField zavarovanec_Kraj;
    @FXML
    private TextField zavarovanec_PSt;
    @FXML
    private DatePicker zavarovanec_DatumRojstva;

    @FXML
    private ToggleGroup zavarovanje_OsnovnoZavarovanje;
    @FXML
    private ToggleGroup zavarovanje_Kasko;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}