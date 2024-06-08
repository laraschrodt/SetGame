package org.example.setgame.buttons;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class BackButton {

    public static void handleBackButtonClick(MouseEvent event, Pane pane) {
        try {
            FXMLLoader loader = new FXMLLoader(BackButton.class.getResource("/org/example/setgame/menu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
