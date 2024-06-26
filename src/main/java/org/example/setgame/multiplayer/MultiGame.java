package org.example.setgame.multiplayer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.setgame.buttons.BackButton;
import org.example.setgame.gameLogic.AnimationHelper;
import org.example.setgame.gameLogic.Cards;
import org.example.setgame.gameLogic.Player;
import org.example.setgame.gameLogic.SetChecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MultiGame {

    ArrayList<Cards> randomDeck = new ArrayList<>();
    private SetChecker setChecker = new SetChecker();

    private int clickCount1 = 0;
    private ImageView[] clickedCards1 = new ImageView[3];
    private Text[] XList1 = new Text[3];

    private int clickCount2 = 0;
    private ImageView[] clickedCards2 = new ImageView[3];
    private Text[] XList2 = new Text[3];

    private Player player1;
    private Player player2;

    @FXML
    public Label animationLabel;
    @FXML
    public Label player1Label;
    @FXML
    public Label player2Label;
    @FXML
    private Pane multiGamePane;
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
    private void handleAddThreeCards(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/setgame/multi-add-cards.fxml"));
        Parent root = loader.load();

        MultiAddThreeCards controller = loader.getController();
        controller.setPlayers(player1, player2);
        controller.setRandomDeck(randomDeck);
        controller.initializeValues();

        Stage stage = (Stage) ((Label) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void handleReloadCards(MouseEvent event) {
        load12Cards();
    }

    @FXML
    private void handleBackButtonClick(MouseEvent event) {
        BackButton.handleBackButtonClick(event, multiGamePane);

        try {
            MultiSavedGames.savePlayers(player1, player2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        load12Cards();
    }

    private void load12Cards() {
        ArrayList<Cards> allCards = Cards.listAllCards();
        Collections.shuffle(allCards);
        randomDeck = allCards;

        ImageView[] cardViews = {card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11};
        for (int i = 0; i < 12; i++) {
            Cards card = randomDeck.get(i);
            Image image = new Image(getClass().getResourceAsStream(card.getImagePath()));
            cardViews[i].setImage(image);
            cardViews[i].setUserData(card);
        }
    }

    @FXML
    private void handleCardClick(MouseEvent event) {
        ImageView clickedCard = (ImageView) event.getSource();
        if (event.getButton() == MouseButton.PRIMARY) {
            handlePlayer1CardClick(clickedCard);
        } else if (event.getButton() == MouseButton.SECONDARY) {
            handlePlayer2CardClick(clickedCard);
        }
    }

    private void handlePlayer1CardClick(ImageView clickedCard) {
        if (clickCount1 < 3) {
            clickedCards1[clickCount1] = clickedCard;
            showXPlayer1(clickedCard, clickCount1);
            clickCount1++;
        }
        if (clickCount1 == 3) {
            if (setChecker.isASet(Arrays.asList(clickedCards1))) {
                AnimationHelper.isSetAnimation(animationLabel, multiGamePane, "Player 1 found a Set!");
                player1.addPoints(1);
                player1Label.setText(player1.getName() + ": " + player1.getPoints());
                cardReloadWhenSet(clickedCards1);
            } else {
                AnimationHelper.isSetAnimation(animationLabel, multiGamePane, "Not a Set!");
            }
            clearXList(XList1);
            clickCount1 = 0;
        }
    }

    private void handlePlayer2CardClick(ImageView clickedCard) {
        if (clickCount2 < 3) {
            clickedCards2[clickCount2] = clickedCard;
            showXPlayer2(clickedCard, clickCount2);
            clickCount2++;
        }
        if (clickCount2 == 3) {
            if (setChecker.isASet(Arrays.asList(clickedCards2))) {
                AnimationHelper.isSetAnimation(animationLabel, multiGamePane, "Player 2 found a Set!");
                player2.addPoints(1);
                player2Label.setText(player2.getName() + ": " + player2.getPoints());
                cardReloadWhenSet(clickedCards2);
            } else {
                AnimationHelper.isSetAnimation(animationLabel, multiGamePane, "Not a Set!");
            }
            clearXList(XList2);
            clickCount2 = 0;
        }
    }

    private void showXPlayer1(ImageView card, int index) {
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

        multiGamePane.getChildren().add(text);
        XList1[index] = text;
    }

    private void showXPlayer2(ImageView card, int index) {
        Text text = new Text("X");
        text.setStyle("-fx-font-size: 50");
        text.setFill(javafx.scene.paint.Color.RED);
        double textWidth = text.getLayoutBounds().getWidth();
        double textHeight = text.getLayoutBounds().getHeight();
        double imageWidth = card.getBoundsInLocal().getWidth();
        double imageHeight = card.getBoundsInLocal().getHeight();

        double x = card.localToScene(card.getBoundsInLocal()).getMinX() + (imageWidth - textWidth) / 2 - 15;
        double y = card.localToScene(card.getBoundsInLocal()).getMinY() + (imageHeight + textHeight) / 2;
        text.setLayoutX(x);
        text.setLayoutY(y);

        multiGamePane.getChildren().add(text);
        XList2[index] = text;
    }

    private void clearXList(Text[] xList) {
        for (Text x : xList) {
            multiGamePane.getChildren().remove(x);
        }
    }

    private void cardReloadWhenSet(ImageView[] clickedCards) {
        ArrayList<Cards> selectedCards = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int randomIndex = random.nextInt(randomDeck.size());
            selectedCards.add(randomDeck.get(randomIndex));
            randomDeck.remove(randomIndex);
        }

        for (int i = 0; i < 3; i++) {
            Cards card = selectedCards.get(i);
            ImageView clickedCard = clickedCards[i];
            Image image = new Image(getClass().getResourceAsStream(card.getImagePath()));
            clickedCard.setImage(image);
            clickedCard.setUserData(card);
        }
    }

    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        player1Label.setText(player1.getName() + ": " + player1.getPoints());
        player2Label.setText(player2.getName() + ": " + player2.getPoints());
    }
}


