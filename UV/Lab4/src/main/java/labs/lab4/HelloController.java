package labs.lab4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class HelloController implements Initializable {
    private List<String> znamkeAvtomobilov;
    private HashMap<String, List<String>> modeliAvtomobilov;
    private List<String> lokacijePrevzema;

    private ToggleGroup vrstaMotorja;
    private ToggleGroup vrstaMenjalnika;
    private ToggleGroup dodatnoZavarovanje;
    private ToggleGroup nacinPlacila;

    private List<Node> izbrani = new ArrayList<>();

    @FXML
    private DatePicker datumOd;
    @FXML
    private DatePicker datumDo;
    @FXML
    private ComboBox lokacijaPrevzema;
    @FXML
    private ComboBox znamkaAvta;
    @FXML
    private ComboBox modelAvta;
    @FXML
    private RadioButton vrstaBencin;
    @FXML
    private RadioButton vrstaDizel;
    @FXML
    private RadioButton vrstaElektrika;
    @FXML
    private RadioButton menjalnikRocni;
    @FXML
    private RadioButton menjalnikAvtomatski;
    @FXML
    private Label cenaNajema;
    @FXML
    private TextField imeNajemnika;
    @FXML
    private TextField priimekNajemnika;
    @FXML
    private TextField naslovNajemnika;
    @FXML
    private TextField hisnaSt;
    @FXML
    private TextField krajNajemnika;
    @FXML
    private TextField postnaSt;
    @FXML
    private TextField eMail;
    @FXML
    private TextField telSt;
    @FXML
    private DatePicker datumRojstva;
    @FXML
    private DatePicker datumPridobitveIzpita;
    @FXML
    private RadioButton dodZavDa;
    @FXML
    private RadioButton dodZavNe;
    @FXML
    private RadioButton placiloKartica;
    @FXML
    private RadioButton placiloGotovina;
    @FXML
    private TextField stKartice;
    @FXML
    private TextField CCV;
    @FXML
    private Label porociloNajema;
    @FXML
    private Label opombe;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datumDo.setOnAction(e -> {
            if (datumOd.getValue() != null) {
                calculatePrice();
            }
        });
        initializeComboBox();
        initializeRadioButtons();
    }

    private void initializeComboBox() {
        znamkeAvtomobilov = new ArrayList<>() {{
            add("BMW");
            add("Audi");
            add("Mercedes");
            add("Mini");
        }};

        modeliAvtomobilov = new HashMap<>() {{
            put("BMW", new ArrayList<>() {{
                add("E30");
                add("X1");
                add("X3");
            }});
            put("Audi", new ArrayList<>() {{
                add("Q8");
                add("SQ5");
                add("Q7");
            }});
            put("Mercedes", new ArrayList<>() {{
                add("S63 AMG");
                add("G Wagon");
                add("SL Roadster");
            }});
            put("Mini", new ArrayList<>() {{
                add("Cooper");
                add("Countryman");
                add("Clubman");
            }});
        }};

        lokacijePrevzema = new ArrayList<>() {{
            add("Ljubljana");
            add("Maribor");
            add("Celje");
            add("Kranj");
            add("Velenje");
            add("Koper");
            add("Novo Mesto");
            add("Murska Sobota");
            add("Jesenice");
            add("Portorož");
            add("letališče Brnik");
            add("letališče Maribor");
        }};


        for (String lokacija : lokacijePrevzema) {
            lokacijaPrevzema.getItems().add(lokacija);
        }

        for (String lokacija : znamkeAvtomobilov) {
            znamkaAvta.getItems().add(lokacija);
        }

        znamkaAvta.setOnAction(e -> {
            String selected = znamkaAvta.getSelectionModel().getSelectedItem().toString();
            modelAvta.getItems().clear();
            modeliAvtomobilov.get(selected).forEach(model -> {
                modelAvta.getItems().add(model);
            });
        });
    }

    private void initializeRadioButtons() {
        vrstaMotorja = new ToggleGroup();
        vrstaBencin.setToggleGroup(vrstaMotorja);
        vrstaDizel.setToggleGroup(vrstaMotorja);
        vrstaElektrika.setToggleGroup(vrstaMotorja);

        vrstaMenjalnika = new ToggleGroup();
        menjalnikRocni.setToggleGroup(vrstaMenjalnika);
        menjalnikAvtomatski.setToggleGroup(vrstaMenjalnika);

        dodatnoZavarovanje = new ToggleGroup();
        dodZavDa.setToggleGroup(dodatnoZavarovanje);
        dodZavNe.setToggleGroup(dodatnoZavarovanje);

        dodatnoZavarovanje.selectedToggleProperty().addListener((arg0, oldValue, newValue) -> {
            calculatePrice();
        });

        nacinPlacila = new ToggleGroup();
        placiloKartica.setToggleGroup(nacinPlacila);
        placiloGotovina.setToggleGroup(nacinPlacila);

        nacinPlacila.selectedToggleProperty().addListener((arg0, oldValue, newValue) -> {
            if (newValue.equals(placiloGotovina)) {
                stKartice.setDisable(true);
                CCV.setDisable(true);
                stKartice.setStyle("");
                CCV.setStyle("");
            } else {
                stKartice.setDisable(false);
                CCV.setDisable(false);
            }
        });

        hisnaSt.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!hisnaSt.getText().matches("^([0-9]{1,3})$")) {
                    hisnaSt.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-text-fill: red;");
                } else {
                    hisnaSt.setStyle("");
                }
            }
        });

        postnaSt.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!postnaSt.getText().matches("^([0-9]{4})$")) {
                    postnaSt.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-text-fill: red;");
                } else {
                    postnaSt.setStyle("");
                }
            }
        });

        eMail.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!eMail.getText().matches("^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$")) {
                    eMail.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-text-fill: red;");
                } else {
                    eMail.setStyle("");
                }
            }
        });

        stKartice.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!stKartice.getText().matches("^([0-9]{4})-([0-9]{4})-([0-9]{4})-([0-9]{4})$")) {
                    stKartice.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-text-fill: red;");
                } else {
                    stKartice.setStyle("");
                }
            }
        });

        CCV.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (!CCV.getText().matches("^([0-9]{3})$")) {
                    CCV.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-text-fill: red;");
                } else {
                    CCV.setStyle("");
                }
            }
        });
    }

    public void onSubmit(ActionEvent e) {
        if (izbrani.size() < 27) {
            try {
                Date dateFrom = new SimpleDateFormat("yyyy-mm-dd").parse(datumOd.getValue().toString());
                Date dateTo = new SimpleDateFormat("yyyy-mm-dd").parse(datumDo.getValue().toString());

                long diffInMillies = Math.abs(dateFrom.getTime() - dateTo.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                RadioButton selectedRadioButton = (RadioButton) nacinPlacila.getSelectedToggle();
                String toggleGroupValue = selectedRadioButton.getText();

                String s = String.format("%s\n\n%s\n%s\n%s",
                        "Poročilo o najemu:",
                        String.format("Ime, priimek: %s, %s", imeNajemnika.getText(), priimekNajemnika.getText()),
                        String.format("Trajanje najema: %s do %s (%d dni)", dateFrom.toString(), dateTo.toString(), diff),
                        toggleGroupValue.equals("Kartica") ? String.format("Številka kartice: XXXX-XXXX-XXXX-%s", stKartice.getText(10, 14)) : "Plačilo z gotovino");
                porociloNajema.setText(s);
                porociloNajema.setStyle("");

            } catch (ParseException parseException) {
                System.out.println(parseException);
            }
        } else {
            porociloNajema.setText("Prosimo izpolnite vsa polja!");
            porociloNajema.setStyle("-fx-text-fill: red;");
        }
    }

    private void calculatePrice()  {
        try {
            Date dateFrom = new SimpleDateFormat("yyyy-mm-dd").parse(datumOd.getValue().toString());
            Date dateTo = new SimpleDateFormat("yyyy-mm-dd").parse(datumDo.getValue().toString());
            if (dateTo.getTime() < dateFrom.getTime()) {
                datumOd.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-text-fill: red;");
                datumDo.setStyle("-fx-border-color: red; -fx-border-width: 1px; -fx-text-fill: red;");
                return;
            }
            datumOd.setStyle("");
            datumDo.setStyle("");
            long diffInMillies = Math.abs(dateFrom.getTime() - dateTo.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            int cena = (int) Math.floor(diff * 100);
            int cenaZavarovanja = (dodZavDa.isSelected()) ? (int) Math.floor(diff * 2) : 0;
            cenaNajema.setText(String.format("%s €", String.valueOf(cena + cenaZavarovanja)));
            opombe.setText("cena najema je 100€/dan, z dodatnim zavarovanjem 2€/dan");
            opombe.setStyle("-fx-font-size: 10px");
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

    public void addToSet(ActionEvent e) {
        izbrani.add((Node) e.getSource());
//        double trenutniStatus = statusNapredka.getProgress();
//        statusNapredka.setProgress(trenutniStatus + 1 / 30);
    }
}