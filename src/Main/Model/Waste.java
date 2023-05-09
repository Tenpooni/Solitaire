package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Waste extends Deck{

    private int flipOver = 3;

    public Waste(String str) {
        super(str);
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

//    //EFFECTS: Adds all cards first into faceDown pile then calls organizeStack to sort
//    @Override
//    public void addCards(ArrayList<Card> cards, boolean toFaceUp) {
//        if (toFaceUp) {
//            Collections.reverse(cards);
//
//            for (Card card : cards) {
//                this.faceUp.add(0, card);
//            }
//        } else {
//            this.faceDown.addAll(cards);
//        }
//    }

    //TODO: FIX THIS
    //EFFECTS: removes given card from faceUp list if faceUp contains cards and index of given is 0.
    @Override
    protected void removeFaceUpCards(ArrayList<Card> cards) {
        //3 condition: Can remove if existing cards in FaceUp and selected card is index 0 of faceUps
        if (cards != null &&
                this.faceUp.size() > 0 &&
                this.faceUp.indexOf(cards.get(0)) == 0) {

            this.faceUp.remove(cards.get(0));
        }
    }

    @Override
    protected void refreshCards() {
        //After removing cards, check if waste deck faceUps is out of cards.
        if (this.faceUp.size() == 0) {
            drawNewFaceUp();
        }
    }

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

        removeFaceDown(cardsToFlip);
        flipCards(cardsToFlip, true);

        addToFaceUpCards(cardsToFlip);
    }

    private void removeFaceDown(ArrayList<Card> cards) {
        if (this.faceDown.size() > 0) {
            this.faceDown.remove(cards.get(0));
        }
    }

    //TODO: for use refreshing draw deck?
    public void setAllFaceDown() {
        ArrayList<Card> faceUps = new ArrayList<>(this.faceUp);
        this.faceUp.clear();
        this.faceDown.addAll(faceUps);
    }

    //TODO: is this necessary?
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

    //Only allow outside card of faceUp deck to be selected (index 0)
    @Override
    protected ArrayList<Card> getSelected(Card c) {
        ArrayList<Card> selectedCards = new ArrayList<>();

        if (this.faceUp.indexOf(c) == 0) {
            selectedCards.add(this.faceUp.get(0));
        }

        return selectedCards;
    }

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
}
