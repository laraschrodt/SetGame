package org.example.setgame.singleplayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.setgame.buttons.BackButton;
import org.example.setgame.gameLogic.Player;

import java.io.*;
import java.util.ArrayList;

import java.io.IOException;

public class SingleSavedGames {

    private static final String gameSaves = "SingleGameSaves";
    public ListView<String> listView;
    public Pane singleSavedGamesPane;

    public void initialize() {
        try {
            ArrayList<Player> players = loadAllPlayers();
            ObservableList<String> playerNames = FXCollections.observableArrayList();
            for (Player player : players) {
                playerNames.add(player.getName());
            }
            listView.setItems(playerNames);
            listView.setOnMouseClicked(event -> handlePlayerClick(event));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackButtonClick(MouseEvent event) {
        BackButton.handleBackButtonClick(event, singleSavedGamesPane);
    }

    private void handlePlayerClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            String selectedPlayerName = listView.getSelectionModel().getSelectedItem();
            if (selectedPlayerName != null) {
                try {
                    Player player = loadPlayer(selectedPlayerName);
                    if (player != null) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/setgame/single-game.fxml"));
                            Parent root = loader.load();

                            SingleGame controller = loader.getController();
                            controller.setPlayer(player);
                            controller.setPlayerPoints(player);

                            Stage stage = (Stage) singleSavedGamesPane.getScene().getWindow();
                            stage.setScene(new Scene(root));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void savePlayer(Player player) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(gameSaves, true))) {
            writer.write(player.getName() + ";" + player.getPoints());
            writer.newLine();
        }
    }

    public static ArrayList<Player> loadAllPlayers() throws IOException {
        ArrayList<Player> playerList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(gameSaves))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    String name = parts[0];
                    int points = Integer.parseInt(parts[1]);
                    playerList.add(new Player(name, points));
                }
            }
        }
        return playerList;
    }

    public static Player loadPlayer(String name) throws IOException {
        ArrayList<Player> playerList = loadAllPlayers();
        for (Player player : playerList) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    @FXML
    private void handleDeleteButtonClick() {
        String selectedPlayerName = listView.getSelectionModel().getSelectedItem();
        if (selectedPlayerName != null) {
            try {
                deletePlayer(selectedPlayerName);
                listView.getItems().remove(selectedPlayerName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deletePlayer(String playerName) throws IOException {
        ArrayList<Player> playerList = loadAllPlayers();
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getName().equals(playerName)) {
                playerList.remove(i);
                break;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(gameSaves))) {
            for (Player player : playerList) {
                writer.write(player.getName() + ";" + player.getPoints());
                writer.newLine();
            }
        }
    }

}