package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stack {
    private final String name;
    private ArrayList<Card> faceUp;
    private ArrayList<Card> faceDown;

    public Stack(String str) {
        this.name = str;
        this.faceUp = new ArrayList<>();
        this.faceDown = new ArrayList<>();
    }

    //EFFECTS: returns unmodifiable list of all cards in deck
    public List<Card> viewAllCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(this.faceUp);
        cards.addAll(this.faceDown);
        return Collections.unmodifiableList(cards);
    }

    //TODO: GUI prints in reverse order, fix this method?
    public List<Card> viewReverseCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(this.faceUp);
        cards.addAll(this.faceDown);
        Collections.reverse(cards);
        return Collections.unmodifiableList(cards);
    }

    //EFFECTS: Adds all cards first into faceDown pile then calls organizeStack to sort
    public void addCards(ArrayList<Card> cards) {
        this.faceDown.addAll(cards);
        organizeStack();
    }

    //EFFECTS: FaceUp cards are kept in order and placed at front of deck, FaceDown cards at back
    private void organizeStack() {
        ArrayList<Card> fullStack = new ArrayList<>();
        fullStack.addAll(this.faceUp);
        fullStack.addAll(this.faceDown);

        ArrayList<Card> faceUps = new ArrayList<>();

        this.faceDown.clear();
        this.faceUp.clear();

        for (Card card : fullStack) {
            if (card.getFaceUp()) {
                faceUps.add(card);
            } else {
                this.faceDown.add(card);
            }
        }

        addToFaceUpStack(faceUps);
    }

    //EFFECTS: selected cards STAY IN ORDER and are appended at index 0 of this deck
    public void addToFaceUpStack(ArrayList<Card> selected) {

        Collections.reverse(selected);

        for (Card card : selected) {
            this.faceUp.add(0, card);
        }
    }

    //REQUIRES: Card c.isFaceUp = True, this.faceUp.contains(Card c)
    //EFFECTS: selected cards put in new list while still maintaining order
    public ArrayList<Card> getSelectedStack(Card c) {

        int i = this.faceUp.indexOf(c);
        ArrayList<Card> selectedCards = new ArrayList<>();

        for(int j = 0; j <= i; j++) {
            selectedCards.add(this.faceUp.get(j));
        }

        return selectedCards;
    }

    //ONLY TO BE CALLED IF SELECTED STACK GETTING MOVED OUT IS VALID
    //EFFECTS: Called by getSelectedStack, removes selected cards from this deck
    public void removeCards(ArrayList<Card> cards, boolean faceUp) {
        if (faceUp) {
            for (Card c : cards) {
                this.faceUp.remove(c);
            }
        } else {
            for (Card c : cards) {
                this.faceDown.remove(c);
            }
        }
    }

    //EFFECTS: flips i amount of lowest index faceDown Cards into faceUp, maintains order.
    public void flipUp(int i) {
        int numFaceDown = this.faceDown.size();
        int toFlip = Math.min(i, numFaceDown);

        ArrayList<Card> cardsToFlip = new ArrayList<>();

        for (int j = 0; j < toFlip; j++) {
            cardsToFlip.add(this.faceDown.get(j));
        }

        removeCards(cardsToFlip, false);
        flipCards(cardsToFlip, true);

        addCards(cardsToFlip);
    }

    //EFFECTS: flips all cards from faceUp to faceDown and puts them at back of faceDown stack
    public void flipDown() {
        ArrayList<Card> cardsToFlip = new ArrayList<>(this.faceUp);
        removeCards(cardsToFlip, true);

        for (Card c : cardsToFlip) {
            c.setFaceDown();
        }

        addCards(cardsToFlip);
    }

    //REQUIRES: cardsToFlip cards are present in stack
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

}
