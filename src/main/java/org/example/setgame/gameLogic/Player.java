package org.example.setgame.gameLogic;

public class Player {

    private String name;
    private int points;

    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    public Player(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setName(String name) {
        this.name = name;
    }

}