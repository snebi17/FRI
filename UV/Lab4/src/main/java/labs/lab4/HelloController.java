package labs.lab4;

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

    private Set<Node> izbrani = new HashSet<>();

    @FXML
    private DatePicker datumOd;
    @FXML
    private Spinner<Integer> casOdUre;
    @FXML
    private Spinner<Integer> casOdMinute;
    @FXML
    private DatePicker datumDo;
    @FXML
    private Spinner<Integer> casDoUre;
    @FXML
    private Spinner<Integer> casDoMinute;
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
    private TextField cenaNajema;
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
    private Button posljiPodatke;
    @FXML
    private ProgressIndicator statusNapredka;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializeComboBox();
        initializeRadioButtons();
        initializeSpinners();
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
    }

    private void initializeRadioButtons() {
        vrstaMotorja = new ToggleGroup();
        vrstaBencin.setToggleGroup(vrstaMotorja);
        vrstaDizel.setToggleGroup(vrstaMotorja);
        vrstaElektrika.setToggleGroup(vrstaMotorja);

        vrstaMenjalnika = new ToggleGroup();
        menjalnikRocni.setToggleGroup(vrstaMotorja);
        menjalnikAvtomatski.setToggleGroup(vrstaMotorja);

        dodatnoZavarovanje = new ToggleGroup();
        dodZavDa.setToggleGroup(dodatnoZavarovanje);
        dodZavNe.setToggleGroup(dodatnoZavarovanje);

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

    private void initializeSpinners() {
        casOdUre.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));

        casOdMinute.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));

        casDoUre.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));

        casDoMinute.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
    }

    public void onSubmit(ActionEvent e) {
        if (izbrani.size() == 30) {

        }
        // create a PDF using those values
    }

    private void calculatePrice()  {
        try {
            Date dateFrom = new SimpleDateFormat("mm/dd/yyyy").parse(datumOd.getPromptText());
            Date dateTo = new SimpleDateFormat("mm/dd/yyyy").parse(datumDo.getPromptText());
            long diffInMillies = Math.abs(dateFrom.getTime() - dateTo.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            int cena = (int) Math.floor(diff * 100);
            int cenaZavarovanja = (dodZavDa.isSelected()) ? (int) Math.floor(diff * 100) : 0;
            cenaNajema.setText(String.valueOf(cena + cenaZavarovanja));
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

    public void addToSet(ActionEvent e) {
        izbrani.add((Node) e.getSource());
        double trenutniStatus = statusNapredka.getProgress();
        statusNapredka.setProgress(trenutniStatus + 1 / 30);
    }
}