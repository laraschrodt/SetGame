package org.example.setgame.otherScreens;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import org.example.setgame.buttons.FxmlLoader;
import org.example.setgame.gameLogic.MusicPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handlePlayClick(MouseEvent event) {
        FxmlLoader.loadFxml("/org/example/setgame/multi-or-singleplayer.fxml", event);
    }

    @FXML
    private void handleGameRulesClick(MouseEvent event) {
        FxmlLoader.loadFxml("/org/example/setgame/game-rules.fxml", event);
    }

    @FXML
    private void handleExitClick(MouseEvent event) {
        Stage stage = (Stage) ((Label) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void handleSavesClicked(MouseEvent event) {
        FxmlLoader.loadFxml("/org/example/setgame/saves-mul-or-single.fxml", event);
    }

    public void handleOptionsClicked(MouseEvent event) {
        FxmlLoader.loadFxml("/org/example/setgame/options.fxml", event);
    }
}


