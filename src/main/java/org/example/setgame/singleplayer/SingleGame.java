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
import org.example.setgame.gameLogic.Player;
import org.example.setgame.gameLogic.SetChecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SingleGame {

    private SetChecker setChecker = new SetChecker();
    ArrayList<Cards> randomDeck = new ArrayList<>();

    private int clickCount = 0;
    private ImageView[] clickedCards = new ImageView[3];
    private Text[] XList = new Text[3];
    private Player player;

    @FXML
    public ImageView backArrow;
    @FXML
    public Label animationLabel;
    @FXML
    public Label pointsLabel;
    @FXML
    private Pane singleGamePane;
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
    private void setPointsLabel() {
        pointsLabel.setText("Points: " + player.getPoints());
    }

    @FXML
    private void handleAddThreeCards(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/setgame/single-add-cards.fxml"));
        Parent root = loader.load();

        SingleAddThreeCards controller = loader.getController();
        controller.setPoints(player.getPoints());
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
        BackButton.handleBackButtonClick(event, singleGamePane);

        try {
            SingleSavedGames.savePlayer(player);
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
        boolean isDuplicate = false;

        for (int i = 0; i < clickCount; i++) {
            if (clickedCards[i] == clickedCard) {
                isDuplicate = true;
                break;
            }
        }

        if (!isDuplicate && clickCount < 3) {
            clickedCards[clickCount] = clickedCard;
            showX(clickedCard, clickCount);
            clickCount++;
        }

        if (clickCount == 3) {
            if (setChecker.isASet(Arrays.asList(clickedCards))) {
                AnimationHelper.isSetAnimation(animationLabel, singleGamePane, "Is a Set!");
                player.addPoints(1);
                setPointsLabel();
                cardReloadWhenSet();
            } else {
                AnimationHelper.isSetAnimation(animationLabel, singleGamePane, "Is not a Set!");
            }
            for (Text x : XList) {
                singleGamePane.getChildren().remove(x);
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

        singleGamePane.getChildren().add(text);
        XList[index] = text;
    }

    private void cardReloadWhenSet() {
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

    public void setPlayerPoints(Player player) {
        this.player = player;
        pointsLabel.setText("Points: " + player.getPoints());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
