package org.example.setgame.otherScreens;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.example.setgame.buttons.BackButton;
import org.example.setgame.buttons.FxmlLoader;

public class SavesMulOrSingle {

    public Pane SavesMulOrSinglePane;

    public void handleSingleSavesClick(MouseEvent mouseEvent) {
        FxmlLoader.loadFxml("/org/example/setgame/single-saved-games.fxml", mouseEvent);
    }

    public void handleMultiSavesClick(MouseEvent mouseEvent) {
        FxmlLoader.loadFxml("/org/example/setgame/multi-saved-games.fxml", mouseEvent);
    }

    @FXML
    private void handleBackButtonClick(MouseEvent event) {
        BackButton.handleBackButtonClick(event, SavesMulOrSinglePane);
    }
}
