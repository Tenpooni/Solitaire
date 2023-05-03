package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final String name;
    private ArrayList<Card> cards;

    public Deck(String str) {
        this.name = str;
        this.cards = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    //EFFECTS: returns unmodifiable list of all cards in deck
    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }

    //EFFECTS: FaceUp cards added from index 0 of deck, FaceDown cards at end
    public void addCards(ArrayList<Card> cards) {
        for (Card card : cards) {
            if (card.getFaceUp()) {
                this.cards.add(0, card);
            } else {
                this.cards.add(card);
            }
        }
    }

    //EFFECTS: returns modifiable arraylist of all faceUp cards in this deck
    public ArrayList<Card> getFaceUpCards() {
        ArrayList<Card> faceUpCards = new ArrayList<>();
        for (Card card : this.cards) {
            if (card.getFaceUp()) {
                faceUpCards.add(0, card);
            }
        }
        return faceUpCards;
    }

    //REQUIRES: Card c.isFaceUp = True, this.cards.contains(Card c)
    //EFFECTS: selected cards put in new list
    public ArrayList<Card> getSelectedStack(Card c) {
        int i = this.cards.indexOf(c);
        ArrayList<Card> selectedCards = new ArrayList<>();

        for(int j = 0; j < i; j++) {
            selectedCards.add(cards.get(j));
        }
        removeFaceUpCards(i);

        return selectedCards;
    }

    //EFFECTS: Called by getSelectedStack, removes selected cards from this deck
    private void removeFaceUpCards(int i) {
        if (i > 0) {
            this.cards.subList(0, i).clear();
        }
    }


    //REQUIRES: ArrayList<Card> selected contains only FaceUp = True cards.
    //EFFECTS:selected cards added to this deck in order of visible at bottom
    public void addToFaceUpStack(ArrayList<Card> selected) {
        Collections.reverse(selected);

        for (Card card : selected) {
            this.cards.add(0, card);
        }
    }

}
