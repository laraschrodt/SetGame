package org.example.setgame.multiplayer;

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

import java.io.IOException;

public class MultiNamePopUp {

    public TextField player1Name;
    public TextField player2Name;
    public Pane namePopUpPane;

    @FXML
    private void handleBackButtonClick(MouseEvent event) {
        BackButton.handleBackButtonClick(event, namePopUpPane);
    }

    @FXML
    private void handleStartButtonClick(MouseEvent event) {
        String name1 = player1Name.getText();
        String name2 = player2Name.getText();

        if (name1 != null && !name1.isEmpty() && name2 != null && !name2.isEmpty()) {
            Player player1 = new Player(name1);
            Player player2 = new Player(name2);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/setgame/multi-game.fxml"));
                Parent root = loader.load();

                MultiGame multiGameController = loader.getController();
                multiGameController.setPlayers(player1, player2);

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
