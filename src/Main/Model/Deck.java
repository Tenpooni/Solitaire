package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Deck{
    protected final String name;
    protected final int turnOver;
    protected ArrayList<Card> faceUp;
    protected ArrayList<Card> faceDown;
    protected Rectangle bound;

    public Deck(String str, int turnOver) {
        this.name = str;
        this.turnOver = turnOver;
        this.faceUp = new ArrayList<>();
        this.faceDown = new ArrayList<>();
    }

    protected abstract void removeCards(ArrayList<Card> cards, boolean faceUp);

    protected abstract void turnOver();

    protected abstract void flipUp(int i);

    //REQUIRES: cardsToFlip cards are present in stack
    protected abstract void flipCards(ArrayList<Card> cardsToFlip, boolean makeFaceUp);

    protected abstract void addCards(ArrayList<Card> cards);

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

}
