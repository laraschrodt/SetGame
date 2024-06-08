package org.example.setgame.otherScreens;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import org.example.setgame.buttons.BackButton;

import java.io.IOException;

public class GameRules {

    @FXML
    private Pane gameRulesPane;

    @FXML
    private void handleBackButtonClick(MouseEvent event) {
        BackButton.handleBackButtonClick(event, gameRulesPane);
    }
}



