package org.example.setgame.otherScreens;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.setgame.buttons.BackButton;
import org.example.setgame.buttons.FxmlLoader;

import java.io.IOException;

public class MulOrSingleplayer {

    @FXML
    private Pane mulOrSingleplayerPane;

    @FXML
    private void handleBackButtonClick(MouseEvent event) {
        BackButton.handleBackButtonClick(event, mulOrSingleplayerPane);
    }

    @FXML
    private void handleOnePlayerClick(MouseEvent event) {
        FxmlLoader.loadFxml("/org/example/setgame/single-name-pop-up.fxml", event);
    }

    @FXML
    private void handleTwoPlayerClick(MouseEvent event) {
        FxmlLoader.loadFxml("/org/example/setgame/multi-name-pop-up.fxml", event);
    }
}
