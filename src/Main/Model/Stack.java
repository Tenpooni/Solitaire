package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stack extends Deck{

    private int flipOver = 1;

    public Stack(String str) {
        super(str);
    }

    public void setFlip(int i) {
        flipOver = i;
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
//        this.faceDown.addAll(cards);
//        organizeStack();
//    }

    //EFFECTS: FaceUp cards are kept in order and placed at front of deck, FaceDown cards at back
    private void organizeStack() {
        ArrayList<Card> fullStack = new ArrayList<>();
        fullStack.addAll(this.faceUp);
        fullStack.addAll(this.faceDown);

        ArrayList<Card> faceUps = new ArrayList<>();

        this.faceDown.clear();
        this.faceUp.clear();

        for (Card card : fullStack) {
            if (card.getFaceUp()) {
                faceUps.add(card);
            } else {
                this.faceDown.add(card);
            }
        }
        addToFaceUpCards(faceUps);
    }

    @Override
    public void addToFaceDownCards(ArrayList<Card> selected) {
        this.faceDown.addAll(selected);
    }

    //EFFECTS: selected cards STAY IN ORDER and are appended at index 0 of this deck
    @Override
    public void addToFaceUpCards(ArrayList<Card> selected) {

        Collections.reverse(selected);

        for (Card card : selected) {
            this.faceUp.add(0, card);
        }
    }


    //REQUIRES: Card c.isFaceUp = True, this.faceUp.contains(Card c)
    //EFFECTS: selected cards from own faceUp list put in new list for holding while still maintaining order
    @Override
    public ArrayList<Card> getSelected(Card c) {

        int i = this.faceUp.indexOf(c);
        ArrayList<Card> selectedCards = new ArrayList<>();

        for(int j = 0; j <= i; j++) {
            selectedCards.add(this.faceUp.get(j));
        }

        return selectedCards;
    }

    //ONLY TO BE CALLED IF SELECTED STACK GETTING MOVED OUT IS VALID
    //EFFECTS: Called by getSelectedStack, removes selected cards from this deck
    @Override
    public void removeFaceUpCards(ArrayList<Card> cards) {
        for (Card c : cards) {
            this.faceUp.remove(c);
            drawNewFaceUp();
        }
    }

    @Override
    protected void refreshCards() {
        //blank
    }


    //ONLY CALLED FOR FACEUP CARDS GETTING REMOVED
    //EFFECTS: If there is no faceUp cards left, turn over
    @Override
    protected void drawNewFaceUp() {
        if (this.faceUp.size() == 0 && this.faceDown.size() >= this.flipOver) {
            flipUp(flipOver);
            //organizeStack();
        }
    }

    //EFFECTS: flips i amount of faceDown Cards into faceUp starting from lowest index, maintains order.
    public void flipUp(int i) {
        int numFaceDown = this.faceDown.size();
        int toFlip = Math.min(i, numFaceDown);

        ArrayList<Card> cardsToFlip = new ArrayList<>();

        for (int j = 0; j < toFlip; j++) {
            cardsToFlip.add(this.faceDown.get(j));
        }

        removeFaceDownCards(cardsToFlip);
        flipCards(cardsToFlip, true);

        addToFaceUpCards(cardsToFlip);
    }

    //HELPER
    private void removeFaceDownCards(ArrayList<Card> cards) {
        for (Card c : cards) {
            this.faceDown.remove(c);
        }
    }

    //REQUIRES: cardsToFlip cards are present in stack
    @Override
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

//    //TODO: CURRENTLY ONLY USED IN TESTS, COMMENTED OUT, MOVE TO WASTE CLASS IN FUTURE
//    //EFFECTS: flips all cards from faceUp to faceDown and puts them at back of faceDown stack
//    public void flipDown() {
//        ArrayList<Card> cardsToFlip = new ArrayList<>(this.faceUp);
//        removeCards(cardsToFlip, true);
//
//        for (Card c : cardsToFlip) {
//            c.setFaceDown();
//        }
//
//        addCards(cardsToFlip);
//    }

    //TODO: CURRENTLY ONLY USED IN TESTS, COMMENTED OUT, MOVE TO OTHER CLASS IN FUTURE
    //EFFECTS: returns unmodifiable list of all cards in deck
    public List<Card> viewAllCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(this.faceUp);
        cards.addAll(this.faceDown);
        return Collections.unmodifiableList(cards);
    }
}
