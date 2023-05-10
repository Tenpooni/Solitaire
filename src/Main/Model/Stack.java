package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stack extends Deck{

    private int flipOver = 1;

    public Stack(String str) {
        super(str);
    }

    //ONLY CALLED FOR FACEUP CARDS GETTING REMOVED
    //EFFECTS: If there is no faceUp cards left, turn over
    @Override
    protected void drawNewFaceUp() {
        if (this.faceUp.size() == 0 && this.faceDown.size() >= this.flipOver) {
            flipUp(flipOver);
        }
    }

    //ONLY TO BE CALLED IF SELECTED STACK GETTING MOVED OUT IS VALID
    //EFFECTS: Called by getSelectedStack, removes selected cards from this deck
    @Override
    public void removeFaceUpCards(ArrayList<Card> cards) {
        for (Card c : cards) {
            this.faceUp.remove(c);
            drawNewFaceUp();
        }
    }

    //EFFECTS: selected cards STAY IN ORDER and are appended at index 0 of this deck
    @Override
    public void addToFaceUpCards(ArrayList<Card> selected) {

        Collections.reverse(selected);

        for (Card card : selected) {
            this.faceUp.add(0, card);
        }
    }

    //HELPER
    private void removeFaceDownCards(ArrayList<Card> cards) {
        for (Card c : cards) {
            this.faceDown.remove(c);
        }
    }

    //EFFECTS: add to faceDowns
    @Override
    public void addToFaceDownCards(ArrayList<Card> selected) {
        this.faceDown.addAll(selected);
    }

    @Override
    protected void refreshCards() {
        //blank
    }

    //REQUIRES: cardsToFlip cards are present in stack
    //EFFECTS: flips cards
    @Override
    public void flipCards(ArrayList<Card> cardsToFlip, boolean makeFaceUp) {
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

    //REQUIRES: Card c.isFaceUp = True, this.faceUp.contains(Card c)
    //EFFECTS: selected cards from own faceUp list put in new list for holding while still maintaining order
    @Override
    public ArrayList<Card> getSelected(Card c) {

        int i = this.faceUp.indexOf(c);
        ArrayList<Card> selectedCards = new ArrayList<>();

        for(int j = 0; j <= i; j++) {
            selectedCards.add(this.faceUp.get(j));
        }

        return selectedCards;
    }

    //HELPER
    //EFFECTS: flips i amount of faceDown Cards into faceUp starting from lowest index, maintains order.
    public void flipUp(int i) {
        int numFaceDown = this.faceDown.size();
        int toFlip = Math.min(i, numFaceDown);

        ArrayList<Card> cardsToFlip = new ArrayList<>();

        for (int j = 0; j < toFlip; j++) {
            cardsToFlip.add(this.faceDown.get(j));
        }

        removeFaceDownCards(cardsToFlip);
        flipCards(cardsToFlip, true);

        addToFaceUpCards(cardsToFlip);
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
}
