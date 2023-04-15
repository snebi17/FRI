package lab1.lab1;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Lab1");
        stage.setScene(scene);

        HelloController controller = fxmlLoader.getController();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> controller.handleShortcuts(event));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}