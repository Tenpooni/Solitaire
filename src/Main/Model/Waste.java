package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Waste extends Deck{

    private int flipOver = 1;

    public Waste(String str) {
        super(str);
    }

    //TODO: GUI prints in reverse order, fix this method?
    public List<Card> viewReverseCards(boolean faceDown) {
        ArrayList<Card> cards = new ArrayList<>(this.faceUp);
        if (faceDown) {
            cards.addAll(this.faceDown);
        }
        Collections.reverse(cards);
        return Collections.unmodifiableList(cards);
    }

    //TODO: this incorporated part of addToFaceUpStack, check if other method still needed.
    //EFFECTS: Adds all cards first into faceDown pile then calls organizeStack to sort
    public void addCards(ArrayList<Card> cards, boolean toFaceUp) {
        if (toFaceUp) {
            Collections.reverse(cards);

            for (Card card : cards) {
                this.faceUp.add(0, card);
            }
        } else {
            this.faceDown.addAll(cards);
        }
    }

    @Override
    protected void removeCards(ArrayList<Card> cards, boolean faceUp) {
        if (faceUp) {
            for (Card c : cards) {
                this.faceUp.remove(c);
                setAllFaceDown();
                drawNewFaceUp();
            }
        } else {
            for (Card c : cards) {
                this.faceDown.remove(c);
            }
        }
    }

    @Override
    protected void drawNewFaceUp() {
        int toFlip = flipOver;

        if (this.faceDown.size() < flipOver) {
            toFlip = this.faceDown.size();
        }

        ArrayList<Card> cardsToFlip = new ArrayList<>();

        for (int j = 0; j < toFlip; j++) {
            cardsToFlip.add(this.faceDown.get(j));
        }

        removeCards(cardsToFlip, false);
        flipCards(cardsToFlip, true);

        addCards(cardsToFlip, true);
    }

    //TODO: for use refreshing draw deck?
    public void setAllFaceDown() {
        ArrayList<Card> faceUps = new ArrayList<>(this.faceUp);
        this.faceUp.clear();
        this.faceDown.addAll(faceUps);
    }

    //TODO: is this necessary?
    @Override
    protected void flipCards(ArrayList<Card> cardsToFlip, boolean makeFaceUp) {
        if (makeFaceUp) {
            for (Card card : cardsToFlip) {
                card.setFaceUp();
            }
        } else {
            for (Card card : cardsToFlip) {
                card.setFaceDown();
            }
        }
    }

    //Only allow outside card of faceUp deck to be selected (index 0)
    @Override
    protected ArrayList<Card> getSelected(Card c) {
        ArrayList<Card> selectedCards = new ArrayList<>();

        if (this.faceUp.indexOf(c) == 0) {
            selectedCards.add(this.faceUp.get(0));
        }

        return selectedCards;
    }

    @Override
    protected void addToFaceUpStack(ArrayList<Card> selected) {
        Collections.reverse(selected);

        for (Card card : selected) {
            this.faceUp.add(0, card);
        }
    }
}
