package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Deck{
    protected final String name;
    protected ArrayList<Card> faceUp;
    protected ArrayList<Card> faceDown;
    protected Rectangle bound;

    public Deck(String str) {
        this.name = str;
        this.faceUp = new ArrayList<>(0);
        this.faceDown = new ArrayList<>(0);
    }

    protected abstract void removeFaceUpCards(ArrayList<Card> cards);

    protected abstract void drawNewFaceUp();

    protected abstract void refreshCards();

    protected abstract ArrayList<Card> getSelected(Card c);

    protected abstract void addToFaceUpCards(ArrayList<Card> selected);


    public void addToFaceDownCards(ArrayList<Card> selected) {
        this.faceDown.addAll(selected);
    }

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

    //EFFECTS: setter, used for Stack GUI when faceUp/faceDown cards are out
    public void setBound(Rectangle bound) {
        this.bound = bound;
    }

    // EFFECTS: return true if the given Point (x,y) is contained within the bounds of this Stack
    public boolean contains(Point point) {
        return this.bound.contains(point);
    }

    //REQUIRES: this.faceUp != null, this.faceDown != null
    //EFFECTS: return true if no faceUp or faceDown cards.
    public boolean isEmpty() {
        return (this.faceDown.size() == 0 && this.faceUp.size() == 0);
    }

    //EFFECTS: returns unmodifiable list of all active cards
    public List<Card> viewFaceUpCards() {
        ArrayList<Card> cards = new ArrayList<>(this.faceUp);
        return Collections.unmodifiableList(cards);
    }

}
