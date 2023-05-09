package Model;

import java.util.ArrayList;

public class Foundation extends Deck{

    public Foundation(String str) {
        super(str);
    }

    @Override
    protected void removeCards(ArrayList<Card> cards, boolean faceUp) {
        Card c = cards.get(0);
        this.faceUp.remove(c);
        drawNewFaceUp();
    }

    //EFFECTS:
    @Override
    protected void drawNewFaceUp() {
        if (this.faceUp.size() == 0 && this.faceDown.size() > 0) {
            Card c = this.faceDown.get(0);
            this.faceDown.remove(c);
            this.faceUp.add(0, c);
        }
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

    //TODO: Duplicated method from addCards, to fix?
    @Override
    public void addToFaceUpStack(ArrayList<Card> selected) {
        if (this.faceUp.size() > 0) {
            Card toFaceDown = this.faceUp.get(0);
            this.faceDown.add(0, toFaceDown);
            this.faceUp.clear();
        }

        Card c = selected.get(0);
        System.out.println(c);
        this.faceUp.add(0, c);
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
