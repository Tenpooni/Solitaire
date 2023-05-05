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
    public List<Card> viewAllCards() {
        return Collections.unmodifiableList(this.cards);
    }

    //EFFECTS: returns unmodifiable list of all faceUp cards in deck
    public List<Card> viewFaceUpCards() {
        ArrayList<Card> faceUpCards = new ArrayList<>();

        for (int i = 0; i < getNumFaceUp(); i++) {
            Card current = this.cards.get(i);
            faceUpCards.add(current);
        }

        return Collections.unmodifiableList(faceUpCards);
    }

    //EFFECTS: FaceUp cards added from index 0 of deck, FaceDown cards added at the end
    public void addCards(ArrayList<Card> cards) {

        ArrayList<Card> faceUpCards = new ArrayList<>();

        for (Card card : cards) {
            if (card.getFaceUp()) {
                faceUpCards.add(card);
            } else {
                this.cards.add(card);
            }
        }
        addToFaceUpStack(faceUpCards);
    }

    //EFFECTS: return amount of cards in deck with FaceUp
    public int getNumFaceUp() {
        int i = 0;
        for (Card c : this.cards) {
            if (c.getFaceUp()) {
                i++;
            }
        }
        return i;
    }

//    //EFFECTS: returns modifiable arraylist of all faceUp cards in this deck
//    public ArrayList<Card> getFaceUpCards() {
//
//        ArrayList<Card> faceUpCards = new ArrayList<>();
//
//        for (Card card : this.cards) {
//            if (card.getFaceUp()) {
//                faceUpCards.add(0, card);
//            }
//        }
//        return faceUpCards;
//    }

    //REQUIRES: Card c.isFaceUp = True, this.cards.contains(Card c)
    //EFFECTS: selected cards put in new list while still maintaining order
    public ArrayList<Card> getSelectedStack(Card c) {

        int i = this.cards.indexOf(c);
        ArrayList<Card> selectedCards = new ArrayList<>();

        for(int j = 0; j <= i; j++) {
            selectedCards.add(cards.get(j));
        }
        removeFaceUpCards(selectedCards);

        return selectedCards;
    }

    //HELPER
    //EFFECTS: Called by getSelectedStack, removes selected cards from this deck
    private void removeFaceUpCards(ArrayList<Card> cards) {
        for (Card c : cards) {
            this.cards.remove(c);
        }
    }

    //REQUIRES: ArrayList<Card> selected contains only FaceUp = True cards.
    //EFFECTS:selected cards STAY IN ORDER and are appended at index 0 of this deck
    public void addToFaceUpStack(ArrayList<Card> selected) {

        Collections.reverse(selected);

        for (Card card : selected) {
            this.cards.add(0, card);
        }
    }

    //EFFECTS: flips i amount of lowest index faceDown Cards into faceUp
    public void flipUp(int i) {
        int numFaceUp = getNumFaceUp();
        int numFaceDown = this.cards.size() - numFaceUp;
        int cardsToFlip = Math.min(i, numFaceDown);

        if (numFaceDown >= 0) {
            for (int j = 0; j < cardsToFlip; j++) {
                this.cards.get(j + numFaceUp).setFaceUp();
            }
        }
    }

    //EFFECTS: faceUp cards flipped to faceDown, and sorted into end of stack
    public void flipDown() {
        ArrayList<Card> cardsToFlip = new ArrayList<>();

        for (Card c : this.cards) {
            if (c.getFaceUp()) {
                c.setFaceDown();
                cardsToFlip.add(c);
            }
        }
        addToFaceDownStack(cardsToFlip);
    }

    private void addToFaceDownStack(ArrayList<Card> toFaceDown) {
        removeFaceUpCards(toFaceDown);
        addCards(toFaceDown);
    }

}
