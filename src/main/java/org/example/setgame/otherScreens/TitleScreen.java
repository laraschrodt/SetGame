package org.example.setgame.otherScreens;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.FadeTransition;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleScreen implements Initializable {

    @FXML
    private Pane titleSreenPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), titleSreenPane);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(event -> {
            switchToTitleScreen();
        });
        fadeTransition.play();
    }

    private void switchToTitleScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/setgame/menu.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) titleSreenPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}