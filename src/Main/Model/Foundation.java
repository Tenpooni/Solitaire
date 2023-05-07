package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Foundation extends Deck{

    public Foundation(String str, int turnOver) {
        super(str, turnOver);
    }

    @Override
    protected void removeCards(ArrayList<Card> cards, boolean faceUp) {
        Card c = cards.get(0);
        this.faceUp.remove(c);
    }

    //EFFECTS:
    @Override
    protected void turnOver() {
        if (this.faceUp.size() == 0 && this.faceDown.size() != 0) {
            flipUp(1);
        }
    }

    //EFFECTS: flip 1 card from faceDown to faceUp
    @Override
    protected void flipUp(int i) {
        int numFaceDown = this.faceDown.size();
        Card c = this.faceDown.get(numFaceDown - 1);

        ArrayList<Card> cardsToFlip = new ArrayList<>();
        cardsToFlip.add(c);

        removeCards(cardsToFlip, false);
        flipCards(cardsToFlip, true);

        addCards(cardsToFlip);
    }

    //EFFECTS: Adding new card always puts it at front of the faceUp list
    @Override
    public void addCards(ArrayList<Card> cards) {
        Card c = cards.get(0);
        this.faceUp.add(0, c);
    }

    @Override
    protected void flipCards(ArrayList<Card> cardsToFlip, boolean makeFaceUp) {
        Card c = cardsToFlip.get(0);
        if (makeFaceUp) {
            c.setFaceUp();
        } else {
            c.setFaceDown();
        }
    }

    @Override
    public void addToFaceUpStack(ArrayList<Card> selected) {
        Card c = selected.get(0);
        this.faceUp.add(0, c);

    }

    //REQUIRES: Card c.isFaceUp = True, this.faceUp.contains(Card c)
    //EFFECTS: selected cards put in new list while still maintaining order
    @Override
    public ArrayList<Card> getSelected(Card c) {
        ArrayList<Card> selectedCards = new ArrayList<>();
        if (this.faceUp.indexOf(c) == 0) {
            selectedCards.add(c);
        }
        return selectedCards;
    }
}
