package org.example.setgame.multiplayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.setgame.buttons.BackButton;
import org.example.setgame.gameLogic.Player;

import java.io.*;
import java.util.ArrayList;

public class MultiSavedGames {

    private static final String multiGameSaves = "multiGameSaves";
    public ListView<String> listView;
    public Pane pane;

    public void initialize() {
        try {
            ArrayList<Player[]> playersList = loadAllPlayers();
            ObservableList<String> playerNames = FXCollections.observableArrayList();
            for (Player[] players : playersList) {
                playerNames.add(players[0].getName() + " & " + players[1].getName());
            }
            listView.setItems(playerNames);
            listView.setOnMouseClicked(event -> handlePlayerClick(event));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handlePlayerClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            String selectedPlayerNames = listView.getSelectionModel().getSelectedItem();
            if (selectedPlayerNames != null) {
                try {
                    String[] names = selectedPlayerNames.split(" & ");
                    Player[] players = loadPlayers(names[0], names[1]);
                    if (players != null) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/setgame/multi-game.fxml"));
                            Parent root = loader.load();

                            MultiGame controller = loader.getController();
                            controller.setPlayers(players[0], players[1]);

                            Stage stage = (Stage) pane.getScene().getWindow();
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

    public static void savePlayers(Player player1, Player player2) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(multiGameSaves, true))) {
            writer.write(player1.getName() + ";" + player1.getPoints() + ";" + player2.getName() + ";" + player2.getPoints());
            writer.newLine();
        }
    }

    public static ArrayList<Player[]> loadAllPlayers() throws IOException {
        ArrayList<Player[]> playerList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(multiGameSaves))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String name1 = parts[0];
                    int points1 = Integer.parseInt(parts[1]);
                    String name2 = parts[2];
                    int points2 = Integer.parseInt(parts[3]);
                    playerList.add(new Player[]{new Player(name1, points1), new Player(name2, points2)});
                }
            }
        }
        return playerList;
    }

    public static Player[] loadPlayers(String name1, String name2) throws IOException {
        ArrayList<Player[]> playerList = loadAllPlayers();
        for (Player[] players : playerList) {
            if (players[0].getName().equals(name1) && players[1].getName().equals(name2)) {
                return players;
            }
        }
        return null;
    }

    @FXML
    private void handleBackButtonClick(MouseEvent event) {
        BackButton.handleBackButtonClick(event, pane);
    }

    @FXML
    private void handleDeleteButtonClick(MouseEvent event) {
        String selectedPlayerNames = listView.getSelectionModel().getSelectedItem();
        if (selectedPlayerNames != null) {
            try {
                deletePlayers(selectedPlayerNames);
                // Nach dem Löschen den Spielstand aus der Liste entfernen und neu laden
                listView.getItems().remove(selectedPlayerNames);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deletePlayers(String selectedPlayerNames) throws IOException {
        ArrayList<Player[]> playerList = loadAllPlayers();
        for (int i = 0; i < playerList.size(); i++) {
            String playerName1 = playerList.get(i)[0].getName();
            String playerName2 = playerList.get(i)[1].getName();
            if ((playerName1 + " & " + playerName2).equals(selectedPlayerNames)) {
                playerList.remove(i);
                break;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(multiGameSaves))) {
            for (Player[] players : playerList) {
                writer.write(players[0].getName() + ";" + players[0].getPoints() + ";" + players[1].getName() + ";" + players[1].getPoints());
                writer.newLine();
            }
        }
    }

}

