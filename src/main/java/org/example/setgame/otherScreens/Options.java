package org.example.setgame.otherScreens;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.example.setgame.buttons.BackButton;
import org.example.setgame.gameLogic.MusicPlayer;

public class Options {

    @FXML
    private Pane pane;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Slider slider;

    private MusicPlayer musicPlayer = MusicPlayer.getInstance();

    @FXML
    public void initialize() {
        checkBox.setSelected(musicPlayer.isMuted());
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            musicPlayer.toggleMute();
        });

        double initialVolume = 0.5;
        slider.setValue(initialVolume * 100);

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            musicPlayer.setVolume(newValue.doubleValue() / 100);
        });
    }

    @FXML
    private void handleBackButtonClick(MouseEvent event) {
        BackButton.handleBackButtonClick(event, pane);
    }
}

