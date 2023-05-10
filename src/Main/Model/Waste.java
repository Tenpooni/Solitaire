package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Waste extends Deck{

    private int flipOver = 3;

    public Waste(String str) {
        super(str);
    }

    //Flips 3 faceDown cards or however many remain in Waste into faceUps
    @Override
    protected void drawNewFaceUp() {
        int toFlip = flipOver;

        if (this.faceDown.size() < flipOver) {
            toFlip = this.faceDown.size();
        }

        ArrayList<Card> cardsToFlip = new ArrayList<>();

        for (int j = 0; j < toFlip; j++) {
            cardsToFlip.add(this.faceDown.get(j));

        }

        this.faceDown.removeAll(cardsToFlip);

        flipCards(cardsToFlip, true);

        addToFaceUpCards(cardsToFlip);
    }

    //EFFECTS: removes given card from faceUp list if faceUp contains cards and index of given is 0.
    @Override
    protected void removeFaceUpCards(ArrayList<Card> cards) {
        //3 condition: Can remove if existing cards in FaceUp and selected card is index 0 of faceUps
        if (cards.size() > 0 &&
                this.faceUp.size() > 0 &&
                this.faceUp.indexOf(cards.get(0)) == 0) {

            this.faceUp.remove(cards.get(0));
        }
    }

    //EFFECTS: adds to deck
    @Override
    protected void addToFaceUpCards(ArrayList<Card> selected) {
        Collections.reverse(selected);

        for (Card card : selected) {
            this.faceUp.add(0, card);
        }
    }

    @Override
    public void addToFaceDownCards(ArrayList<Card> selected) {
        this.faceDown.addAll(selected);
    }

    //EFFECTS: check if waste deck faceUps is out of cards, draw new
    @Override
    protected void refreshCards() {
        if (this.faceUp.size() == 0) {
            drawNewFaceUp();
        }
    }

    @Override
    protected void flipCards(ArrayList<Card> cardsToFlip, boolean makeFaceUp) {
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

    //EFFECTS: Only allow outside card of faceUp deck to be selected (index 0)
    @Override
    protected ArrayList<Card> getSelected(Card c) {
        ArrayList<Card> selectedCards = new ArrayList<>();

        if (this.faceUp.indexOf(c) == 0) {
            selectedCards.add(this.faceUp.get(0));
        }

        return selectedCards;
    }



    //EFFECTS: sets all faceUp into faceDown cards, no removals
    public void setAllFaceDown() {
        ArrayList<Card> faceUps = new ArrayList<>(this.faceUp);
        this.faceUp.clear();
        this.faceDown.addAll(faceUps);
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
}
