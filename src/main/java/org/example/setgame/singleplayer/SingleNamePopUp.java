package org.example.setgame.singleplayer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.setgame.buttons.BackButton;
import org.example.setgame.gameLogic.Player;
import org.example.setgame.multiplayer.MultiGame;

import java.io.IOException;

public class SingleNamePopUp {

    public Pane namePopUpPane;
    public TextField playerName;

    @FXML
    private void handleBackButtonClick(MouseEvent event) {
        BackButton.handleBackButtonClick(event, namePopUpPane);
    }
    
    public void handleStartButtonClick(MouseEvent mouseEvent) {
        String name = playerName.getText();

        if (name != null && !name.isEmpty()) {
            Player player = new Player(name);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/setgame/single-game.fxml"));
                Parent root = loader.load();

                SingleGame singleGameController = loader.getController();
                singleGameController.setPlayers(player);

                Stage stage = (Stage) namePopUpPane.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Beide Spielernamen müssen ausgefüllt werden!");
        }
    }
}
