package org.example.setgame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader titleScreenLoader = new FXMLLoader(Application.class.getResource("title-screen.fxml"));

        Scene scene = new Scene(titleScreenLoader.load());

        // Image icon = new Image(Deck.class.getResourceAsStream("/org/example/setgame/images/set_icon.png"));

        stage.setTitle("Set Game");
        // stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}