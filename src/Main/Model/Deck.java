package Model;

import java.awt.*;
import java.util.ArrayList;

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


//    @Override
//    public ArrayList<Card> getSelected(Card c) {
//
//        int i = this.faceUp.indexOf(c);
//        ArrayList<Card> selectedCards = new ArrayList<>();
//
//        for(int j = 0; j <= i; j++) {
//            selectedCards.add(this.faceUp.get(j));
//        }
//
//        return selectedCards;
//    }
}
