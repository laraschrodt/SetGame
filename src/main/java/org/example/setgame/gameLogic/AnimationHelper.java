package org.example.setgame.gameLogic;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class AnimationHelper {

    public static void isSetAnimation(Label animationLabel, Pane pane, String string) {
        animationLabel.setText(string);
        animationLabel.setVisible(true);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(animationLabel.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(animationLabel.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(1.0), new KeyValue(animationLabel.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(1.5), new KeyValue(animationLabel.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(2.0), new KeyValue(animationLabel.opacityProperty(), 1.0))
        );

        timeline.setCycleCount(1);
        timeline.setOnFinished(event -> animationLabel.setVisible(false));
        timeline.play();
    }
}

