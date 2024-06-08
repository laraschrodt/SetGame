package org.example.setgame.gameLogic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class Cards {

    private String color;
    private String shape;
    private String pattern;
    private int amount;
    private String imagePath;
    private ArrayList<Cards> allCards;

    public Cards(String color, String shape, int amount, String pattern, String imagePath) {
        this.color = color;
        this.shape = shape;
        this.amount = amount;
        this.pattern = pattern;
        this.imagePath = imagePath;
    }

    public static ArrayList<Cards> listAllCards() {
        String imgPath1 = "/org/example/setgame/images/cards/s";
        String imgPath2 = ".png";

        String[] colors = {"red", "green", "blue"};
        String[] shapes = {"diamond", "square", "circle"};
        String[] pattern = {"solid", "striped", "empty"};
        int[] amounts = {0, 1, 2};

        ArrayList<Cards> allCards = new ArrayList<>();

        for (int amountIndex = 0; amountIndex < amounts.length; amountIndex++) {
            for (int fillIndex = 0; fillIndex < pattern.length; fillIndex++) {
                for (int colorIndex = 0; colorIndex < colors.length; colorIndex++) {
                    for (int shapeIndex = 0; shapeIndex < shapes.length; shapeIndex++) {
                        String imagePath = imgPath1 + amounts[amountIndex] + fillIndex + colorIndex + shapeIndex + imgPath2;
                        allCards.add(new Cards(colors[colorIndex], shapes[shapeIndex], amountIndex+1, pattern[fillIndex], imagePath));
                    }
                }
            }
        }

        return allCards;
    }

    public ArrayList<Cards> getAllCards() {
        return allCards;
    }
    public void setAllCards(ArrayList<Cards> allCards) {
        this.allCards = listAllCards();
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPattern() {
        return pattern;
    }
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getShape() {
        return shape;
    }
    public void setShape(String shape) {
        this.shape = shape;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

}

