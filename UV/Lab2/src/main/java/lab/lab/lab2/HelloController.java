package lab.lab.lab2;

import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController implements Initializable {

    private StringBuilder stringOfOperations;
    private ArrayList<String> historyArray;
    private ArrayList<String> actionsArray;

    @FXML
    private GridPane calculator;
    @FXML
    private TextField calculations;
    @FXML
    private Accordion accordion;
    @FXML
    private TitledPane selected;
    @FXML
    private TitledPane historyPane;
    @FXML
    private TextArea history;
    @FXML
    private TitledPane loggerPane;
    @FXML
    private TextArea logger;
    @FXML
    private Label label;

    public void onOpen() {
        FileChooser fileChooser = new FileChooser();
        accordion.setExpandedPane(loggerPane);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            readFromFile(file);
            String name = file.getName();
            long size = file.length();
            String msg = name + " " + size + "B";
            displayMessage(msg);
        } else {
            label.setStyle("-fx-text-fill: red;");
            displayMessage("Napaka pri odpiranju datoteke!");
        }
        actionsArray.add("Odprta datoteka");
    }

    private void readFromFile(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            historyArray.clear();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                historyArray.add(line);
            }
        } catch (IOException e) {
            label.setStyle("-fx-text-fill: red;");
            displayMessage("Napaka pri odpiranju datoteke!");
        }
    }

    public void onSave()  {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                for (String evaluation : historyArray) {
                    bufferedWriter.write(evaluation + "\n");
                }
                bufferedWriter.close();
                displayMessage("Shranjena je bila datoteka: " + file.getName());
            } catch (IOException e) {
                label.setStyle("-fx-text-fill: red;");
                displayMessage("Napaka pri shranjevanju datoteke!");
            }
        }
        actionsArray.add("Shranjena datoteka");
    }

    public void onExit() { System.exit(0); }

    public void onDelete() {
        calculations.setText("");
        logger.setText("");
        actionsArray.clear();
    }

    public void onDisplayAuthor() {
        displayMessage("Avtor: Nejc Šneberger, 63210324");
        actionsArray.add("Informacije o avtorju");
    }

    private static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    private void displayMessage(String msg) {
        label.setText(msg);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
            label.setText("");
            label.setStyle("-fx-text-fill: black");
        });
        pause.play();
    }

    private void saveHistory() {
        historyArray.add(stringOfOperations.toString());
    }

    private void displayHistory() {
        StringBuilder hstr = new StringBuilder();
        for (String expr : historyArray) {
            hstr.append(expr + "\n");
        }
        history.setText(hstr.toString());
    }

    private void saveAction(String action) {
        actionsArray.add(action);
    }

    private void displayActions() {
        StringBuilder acts = new StringBuilder();
        for (String act : actionsArray) {
            acts.append(act + "\n");
        }
        logger.setText(acts.toString());
    }

    private void saveResult(double result) {
        stringOfOperations.delete(0, stringOfOperations.length());
        stringOfOperations.append(result);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Node> btns = calculator.getChildren();
        stringOfOperations = new StringBuilder();
        historyArray = new ArrayList<>();
        actionsArray = new ArrayList<>();

        historyPane.expandedProperty().addListener((obs, wasExpanded, isExpanded) -> {
            if (isExpanded) {
                displayHistory();
            }
        });

        loggerPane.expandedProperty().addListener((obs, wasExpanded, isExpanded) -> {
            if (isExpanded) {
                displayActions();
            }
        });

        EventHandler<MouseEvent> onClick = event -> {
            Button btn = (Button) event.getSource();
            if (btn.getText().equals("=")) {
                saveHistory();
                double result = eval(stringOfOperations.toString());
                saveAction(Double.toString(result));
                saveResult(result);
            } else if (btn.getText().equals("CE")) {
                calculations.setText("");
                return;
            } else {
                stringOfOperations.append(btn.getText());
            }
            calculations.setText(stringOfOperations.toString());
        };

        for (Node btn : btns) {
            btn.addEventHandler(MouseEvent.MOUSE_CLICKED, onClick);
        }

        accordion.setExpandedPane(selected);
    }
}