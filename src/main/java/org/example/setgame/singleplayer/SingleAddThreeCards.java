package org.example.setgame.singleplayer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.setgame.buttons.BackButton;
import org.example.setgame.gameLogic.AnimationHelper;
import org.example.setgame.gameLogic.Cards;
import org.example.setgame.gameLogic.SetChecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SingleAddThreeCards {

    ArrayList<Cards> randomDeck = new ArrayList<>();
    private int clickCount = 0;
    private ImageView[] clickedCards = new ImageView[3];
    private Text[] XList = new Text[3];
    private SetChecker setChecker = new SetChecker();
    int points = 0;

    @FXML
    public Label animationLabel;
    @FXML
    public Label pointsLabel;
    @FXML
    private Pane singleAddThreeCards;
    @FXML
    private ImageView card0;
    @FXML
    private ImageView card1;
    @FXML
    private ImageView card2;
    @FXML
    private ImageView card3;
    @FXML
    private ImageView card4;
    @FXML
    private ImageView card5;
    @FXML
    private ImageView card6;
    @FXML
    private ImageView card7;
    @FXML
    private ImageView card8;
    @FXML
    private ImageView card9;
    @FXML
    private ImageView card10;
    @FXML
    private ImageView card11;
    @FXML
    private ImageView card12;
    @FXML
    private ImageView card13;
    @FXML
    private ImageView card14;

    public void setPoints(int points) {
        this.points = points;
    }

    public void setRandomDeck(ArrayList<Cards> randomDeck) {
        this.randomDeck = randomDeck;
    }

    public void initializeValues() {
        setPointsLabel();
        addThreeCards();
    }

    private void setPointsLabel() {
        pointsLabel.setText("Points: " + points);
    }

    private void addThreeCards() {
        ImageView[] cardViews = {card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14};
        for (int i = 0; i < 15; i++) {
            Cards card = randomDeck.get(i);
            Image image = new Image(getClass().getResourceAsStream(card.getImagePath()));
            cardViews[i].setImage(image);
            cardViews[i].setUserData(card);
        }
    }

    @FXML
    private void handleCardClick(MouseEvent event) {
        ImageView clickedCard = (ImageView) event.getSource();
        if (clickCount < 3) {
            clickedCards[clickCount] = clickedCard;
            showX(clickedCard, clickCount);
            clickCount++;
        }
        if (clickCount == 3) {
            if (setChecker.isASet(Arrays.asList(clickedCards))) {
                AnimationHelper.isSetAnimation(animationLabel, singleAddThreeCards, "Is a Set!");
                points++;
                setPointsLabel();
            } else {
                AnimationHelper.isSetAnimation(animationLabel, singleAddThreeCards, "Is not a Set!");
            }
            for (Text x : XList) {
                singleAddThreeCards.getChildren().remove(x);
            }
            clickCount = 0;
        }
    }

    private void showX(ImageView card, int index) {
        Text text = new Text("X");
        text.setStyle("-fx-font-size: 50");
        double textWidth = text.getLayoutBounds().getWidth();
        double textHeight = text.getLayoutBounds().getHeight();
        double imageWidth = card.getBoundsInLocal().getWidth();
        double imageHeight = card.getBoundsInLocal().getHeight();

        double x = card.localToScene(card.getBoundsInLocal()).getMinX() + (imageWidth - textWidth) / 2 - 15;
        double y = card.localToScene(card.getBoundsInLocal()).getMinY() + (imageHeight + textHeight) / 2;
        text.setLayoutX(x);
        text.setLayoutY(y);

        singleAddThreeCards.getChildren().add(text);
        XList[index] = text;
    }

    @FXML
    private void handleReloadCards(MouseEvent event) {
        ArrayList<Cards> allCards = Cards.listAllCards();
        Collections.shuffle(allCards);
        randomDeck = allCards;

        ImageView[] cardViews = {card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14};
        for (int i = 0; i < 15; i++) {
            Cards card = randomDeck.get(i);
            Image image = new Image(getClass().getResourceAsStream(card.getImagePath()));
            cardViews[i].setImage(image);
            cardViews[i].setUserData(card);
        }
    }

    @FXML
    private void handleBackButtonClick(MouseEvent event) {
        BackButton.handleBackButtonClick(event, singleAddThreeCards);
    }
}
