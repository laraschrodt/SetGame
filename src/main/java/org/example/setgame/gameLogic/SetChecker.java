package org.example.setgame.gameLogic;

import javafx.scene.image.ImageView;

import java.util.List;

public class SetChecker {

    public boolean isASet(List<ImageView> clickedImages) {
        Cards card1 = extractCard(clickedImages.get(0));
        Cards card2 = extractCard(clickedImages.get(1));
        Cards card3 = extractCard(clickedImages.get(2));

        boolean colorSame = isColorSame(card1, card2, card3);
        boolean colorDifferent = isColorDifferent(card1, card2, card3);
        boolean shapeSame = isShapeSame(card1, card2, card3);
        boolean shapeDifferent = isShapeDifferent(card1, card2, card3);

        boolean patternSame = isPatternSame(card1, card2, card3);
        boolean patternDifferent = isPatternDifferent(card1, card2, card3);

        boolean amountSame = isAmountSame(card1, card2, card3);
        boolean amountDifferent = isAmountDifferent(card1, card2, card3);

        return (colorSame || colorDifferent) && (shapeSame || shapeDifferent) && (patternSame || patternDifferent) && (amountSame || amountDifferent);
    }

    private Cards extractCard(ImageView imageView) {
        return (Cards) imageView.getUserData();
    }

    private boolean isColorSame(Cards card1, Cards card2, Cards card3) {
        return card1.getColor().equals(card2.getColor()) && card1.getColor().equals(card3.getColor());
    }
    private boolean isColorDifferent(Cards card1, Cards card2, Cards card3) {
        return !card1.getColor().equals(card2.getColor()) && !card1.getColor().equals(card3.getColor()) && !card2.getColor().equals(card3.getColor());
    }

    private boolean isShapeSame(Cards card1, Cards card2, Cards card3) {
        return card1.getShape().equals(card2.getShape()) && card1.getShape().equals(card3.getShape());
    }
    private boolean isShapeDifferent(Cards card1, Cards card2, Cards card3) {
        return !card1.getShape().equals(card2.getShape()) && !card1.getShape().equals(card3.getShape()) && !card2.getShape().equals(card3.getShape());
    }

    private boolean isPatternSame(Cards card1, Cards card2, Cards card3) {
        return card1.getPattern().equals(card2.getPattern()) && card1.getPattern().equals(card3.getPattern());
    }
    private boolean isPatternDifferent(Cards card1, Cards card2, Cards card3) {
        return !card1.getPattern().equals(card2.getPattern()) && !card1.getPattern().equals(card3.getPattern()) && !card2.getPattern().equals(card3.getPattern());
    }

    private boolean isAmountSame(Cards card1, Cards card2, Cards card3) {
        return card1.getAmount() == card2.getAmount() && card1.getAmount() == card3.getAmount();
    }
    private boolean isAmountDifferent(Cards card1, Cards card2, Cards card3) {
        return card1.getAmount() != card2.getAmount() && card1.getAmount() != card3.getAmount() && card2.getAmount() != card3.getAmount();
    }
}