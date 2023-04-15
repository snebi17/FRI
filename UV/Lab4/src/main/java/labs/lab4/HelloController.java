package labs.lab4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private HashMap<Integer, HashMap<String, ArrayList<String>>> stepMap;

    private HashMap<Integer, String> sceneMap = new HashMap<>() {{
        put(1, "steps/step-one.fxml");
        put(2, "steps/step-two.fxml");
        put(3, "steps/step-three.fxml");
        put(4, "steps/step-four.fxml");
        put(5, "steps/step-five.fxml");
        put(6, "steps/step-six.fxml");
        put(7, "steps/step-seven.fxml");
        put(8, "steps/step-eight.fxml");
    }};

    private int stepCounter = 1;
    @FXML private Pane contentWrapper;

    @FXML private Button nextButton;
    @FXML private Button prevButton;

    @FXML
    private DatePicker datumOd;
    @FXML
    private DatePicker datumDo;
    @FXML
    private ComboBox lokacijaPrevzema;
    @FXML
    private ToggleGroup tipAvta;
    @FXML
    private ComboBox modelAvta;
    @FXML
    private ToggleGroup vrstaGoriva;
    @FXML
    private ToggleGroup vrstaMenjalnika;
    @FXML
    private Label cenaNajema;
    @FXML
    private Label imeOsebe;
    @FXML
    private Label priimekOsebe;
    @FXML
    private Label prebivalisceOsebe;
    @FXML
    private Label krajPrebivalisca;
    @FXML
    private Label postnaStevilkaKraja;
    @FXML
    private Label ePostaOsebe;
    @FXML
    private Label telStOsebe;
    @FXML
    private Label datumRojstva;
    @FXML
    private Label datumPridobitveIzpita;
    @FXML
    private ToggleGroup dodatnoZavarovanje;
    @FXML
    private ToggleGroup nacinPlacila;
    @FXML
    private Label stKreditneKartice;
    @FXML
    private Label CCV;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nextButton.setOnAction(event -> nextScene());
        prevButton.setOnAction(event -> previousScene());

        try {
            Parent content = FXMLLoader.load(getClass().getResource(sceneMap.get(stepCounter)));
            contentWrapper.getChildren().setAll(content);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private void nextScene() {
        if (stepCounter == sceneMap.size()) return;
        try {
            stepCounter++;
            Parent content = FXMLLoader.load(getClass().getResource(sceneMap.get(stepCounter)));
            contentWrapper.getChildren().setAll(content);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void previousScene() {
        if (stepCounter == 1) return;
        try {
            stepCounter--;
            Parent content = FXMLLoader.load(getClass().getResource(sceneMap.get(stepCounter)));
            contentWrapper.getChildren().setAll(content);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}