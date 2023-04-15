package lab1.lab1;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {
    public Menu menuName;
    public Menu menuSurname;
    public ToolBar toolbarFName;

    public TextField input;
    public ComboBox combo;
    public Button sort;

    ToggleGroup toggleGroup;
    public RadioButton add;
    public RadioButton removeFirst;
    public RadioButton removeSelected;

    public boolean allowDuplicates = true;

    int index;
    public Spinner spinner;
    public Label messages;
    public Label status;

    final KeyCombination open = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
    final KeyCombination remove = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
    final KeyCombination exit = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);

    public void handleShortcuts(KeyEvent event) {
        if (open.match(event)) {
            onOpen();
        } else if (remove.match(event)) {
            onRemove();
        } else if (exit.match(event)) {
            onExit();
        }
    }

    public void onOpen() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) status.setText(file.getName());
    }

    public void onRemove() {
        messages.setText("");
        status.setText("");
    }

    public void onExit() {
        System.exit(0);
    }

    public void executeAction() {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        if (selectedRadioButton == null) {
            updateMessage("Niste izbrali nobene akcije!", true);
            return;
        }
        String text = selectedRadioButton.getText();

        switch (text) {
            case "Dodaj": {
                updateMessage("Dodajam...", false);
                String city = input.getText();
                if (city.isEmpty()) {
                    updateMessage("Niste vpisali mesta!", true);
                    break;
                }
                if (allowDuplicates || !combo.getItems().contains(city)) {
                    combo.getItems().add(city);
                } else if (!allowDuplicates) {
                    updateMessage("Dvojniki so prepovedani!", true);
                }
                break;
            }
            case "Odstrani prvega": {
                updateMessage("Odstranjujem prvega...", false);
                if (combo.getItems().size() > 0) {
                    combo.getItems().remove(0, 1);
                    combo.getSelectionModel().selectFirst();
                } else {
                    updateMessage("Seznam mest je prazen ali pa ste\nposkusili odstraniti neobstoječe\nmesto!", true);
                }
                break;
            }
            case "Odstrani izbranega": {
                String city = messages.getText();
                updateMessage("Odstranjujem izbranega...", false);
                if (combo.getItems().size() > 0 && !city.equals("Ni elementa")) {
                    combo.getItems().remove(city);
                    combo.getSelectionModel().selectFirst();
                } else {
                    updateMessage("Seznam mest je prazen ali pa ste\nposkusili odstraniti neobstoječe\nmesto!", true);
                }
                break;
            }
            default: break;
        }
    }

    public void toggleDuplicates() {
        allowDuplicates = !allowDuplicates;
    }

    public void updateMessage(String message, boolean error) {
        if (error) {
            messages.setStyle("-fx-text-fill: red");
        }
        messages.setText(message);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            if (combo.getItems().size() == 0) {
                messages.setText("");
            } else {
                messages.setText(combo.getItems().get(index).toString());
            }
            messages.setStyle("-fx-text-fill: black");
        });
        pause.play();
    }

    private void sortCities() {
        ArrayList<String> cities_ = new ArrayList(combo.getItems());
        Collections.sort(cities_);
        ObservableList<String> cities = FXCollections.observableArrayList(cities_);
        combo.setItems(cities);
        combo.getSelectionModel().selectFirst();
    }

    private void displayLetters(String text) {
        status.setText(status.getText() + text.split(" ")[1]);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Button> toolbarButtons = new ArrayList(toolbarFName.getItems());
        toolbarButtons.forEach((Button b) -> {
            b.setOnMouseClicked(event -> displayLetters(b.getText()));
        });

        ArrayList<MenuItem> menuItems = new ArrayList(menuName.getItems());
        menuItems.addAll(new ArrayList(menuSurname.getItems()));
        menuItems.forEach((MenuItem i) -> {
             i.setOnAction(event -> displayLetters(i.getText()));
        });

        combo.getItems().addAll("Ljubljana", "Maribor", "Koper", "Novo mesto", "Kranj", "Celje", "Krško", "Velenje", "Bijeljina");
        combo.getSelectionModel().selectFirst();
        EventHandler<MouseEvent> sortHandler = event -> sortCities();
        sort.setOnMouseClicked(sortHandler);

        spinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 20, 0, 1));
        updateMessage(combo.getItems().get(0).toString(), false);
        spinner.valueProperty().addListener((obs, oldV, newV) -> {

            index = (int) Double.parseDouble(newV.toString());
            if (index >= combo.getItems().size()) {
                updateMessage("Ni elementa", false);
            } else {
                updateMessage(combo.getItems().get(index).toString(), false);
            }
        });

        toggleGroup = new ToggleGroup();
        add.setToggleGroup(toggleGroup);
        removeFirst.setToggleGroup(toggleGroup);
        removeSelected.setToggleGroup(toggleGroup);
    }
}