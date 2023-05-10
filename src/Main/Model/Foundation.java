package Model;

import java.util.ArrayList;

public class Foundation extends Deck{

    public Foundation(String str) {
        super(str);
    }

    //EFFECTS: moves one card from faceDown to faceUp
    @Override
    protected void drawNewFaceUp() {
        if (this.faceUp.size() == 0 && this.faceDown.size() > 0) {
            Card c = this.faceDown.get(0);
            this.faceDown.remove(c);
            this.faceUp.add(0, c);
        }
    }

    //EFFECTS: Foundation deck only removes index zero faceUp cards
    @Override
    protected void removeFaceUpCards(ArrayList<Card> cards) {
        if (cards.size() > 0) {
            Card c = cards.get(0);
            this.faceUp.remove(c);
        }
    }

    //EFFECTS: Add card to faceUp, move existing faceUps to faceDown
    @Override
    public void addToFaceUpCards(ArrayList<Card> selected) {
        if (this.faceUp.size() > 0) {
            Card toFaceDown = this.faceUp.get(0);
            this.faceDown.add(0, toFaceDown);
            this.faceUp.clear();
        }

        Card c = selected.get(0);
        this.faceUp.add(0, c);
    }

    //EFFECTS: adds to faceDown cards
    @Override
    public void addToFaceDownCards(ArrayList<Card> selected) {
        this.faceDown.addAll(selected);
    }



    //EFFECTS: If there are no faceUp cards displayed, draw 1 faceUp
    @Override
    protected void refreshCards() {
        if (this.faceUp.size() == 0) {
            drawNewFaceUp();
        }
    }

    @Override
    protected void flipCards(ArrayList<Card> cardsToFlip, boolean makeFaceUp) {
        //
    }

    //REQUIRES: Card c.isFaceUp = True, this.faceUp.contains(Card c)
    //EFFECTS: gives selected card from own faceUp list
    @Override
    public ArrayList<Card> getSelected(Card c) {
        ArrayList<Card> selectedCards = new ArrayList<>();
        if (this.faceUp.indexOf(c) == 0) {
            selectedCards.add(c);
        }
        return selectedCards;
    }
}
