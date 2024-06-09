package org.example.setgame.buttons;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlLoader {

    public static void loadFxml(String path, MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(FxmlLoader.class.getResource(path));
            Parent root = loader.load();

            Stage stage = (Stage) ((Label) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
