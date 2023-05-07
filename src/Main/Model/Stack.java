package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Stack {
    private final String name;
    private final int turnOver;
    private ArrayList<Card> faceUp;
    private ArrayList<Card> faceDown;
    private Rectangle bound;

    public Stack(String str, int turnOver) {
        this.name = str;
        this.turnOver = turnOver;
        this.faceUp = new ArrayList<>();
        this.faceDown = new ArrayList<>();
    }

    //EFFECTS: setter, used for Stack GUI when faceUp/faceDown cards are out
    public void setBound(Rectangle bound) {
        this.bound = bound;
    }

    // EFFECTS: return true if the given Point (x,y) is contained within the bounds of this Stack
    public boolean contains(Point point) {
        return this.bound.contains(point);
    }

    //TODO: Make abstract method, implement in inheritor?
    //REQUIRES: this.faceUp != null, this.faceDown != null
    //EFFECTS: return true if no faceUp or faceDown cards.
    public boolean isEmpty() {
        return (this.faceDown.size() == 0 && this.faceUp.size() == 0);
    }

    //TODO: MADE JUST FOR TEST??
    //EFFECTS: returns unmodifiable list of all cards in deck
    public List<Card> viewAllCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(this.faceUp);
        cards.addAll(this.faceDown);
        return Collections.unmodifiableList(cards);
    }

    //EFFECTS: returns unmodifiable list of all active cards
    public List<Card> viewFaceUpCards() {
        ArrayList<Card> cards = new ArrayList<>(this.faceUp);
        return Collections.unmodifiableList(cards);
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
                turnOver();
            }
        } else {
            for (Card c : cards) {
                this.faceDown.remove(c);
            }
        }

    }

    ////TODO: Make abstract method, implement in inheritor?
    //ONLY CALLED FOR FACEUP CARDS GETTING REMOVED
    //EFFECTS: If there is no faceUp cards left, turn over
    private void turnOver() {
        if (this.faceUp.size() == 0 && this.faceDown.size() >= turnOver) {
            flipUp(turnOver);
            organizeStack();
        }
    }

    //EFFECTS: flips i amount of faceDown Cards into faceUp starting from lowest index, maintains order.
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
