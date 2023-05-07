package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Solitaire {

    //Full Deck to draw cards from
    private final ArrayList<Card> fullDeck = new ArrayList<>();

    //List of Interactive decks
    private ArrayList<Stack> decks = new ArrayList<>();

    private Card prevC;
    private Stack prevS;

    private final Stack s1;
    private final Stack s2;
    private final Stack s3;
    private final Stack s4;
    private final Stack s5;
    private final Stack s6;
    private final Stack s7;

    //TODO: FIX WASTE
    private final Stack waste;

    public Solitaire() {
        this.s1 = new Stack("S1", 1);
        this.s2 = new Stack("S2", 1);
        this.s3 = new Stack("S3", 1);
        this.s4 = new Stack("S4", 1);
        this.s5 = new Stack("S5", 1);
        this.s6 = new Stack("S6", 1);
        this.s7 = new Stack("S7", 1);
        this.waste = new Stack("Waste", 3);

        makeDeck();
        initiateDeck();
        System.out.println(fullDeck.size());
    }

    public ArrayList<Stack> getAllDecks() {
        ArrayList<Stack> allDeck = new ArrayList<>(decks);
        allDeck.add(this.waste);
        return allDeck;
    }

    public ArrayList<Stack> getPlayingDecks() {
        return decks;
    }

    public Stack getWasteDeck() {
        return waste;
    }

    //EFFECTS: clears current selected card
    public void deselectAll() {
        if (this.prevC != null) {
            this.prevC.deSelect();
        }
    }

    //EFFECTS: checks if current selection (Card) exists,
    //if next selection is valid then move selected stack, otherwise set as next selection
    public void setSelection(Card nextC, Stack nextS) {
        if (this.prevC != null) {
            checkMoveCards(prevC, prevS, nextC, nextS);
        }
        nextC.select();
        this.prevC = nextC;
        this.prevS = nextS;
    }

    //TODO: add restrictions to this method when moving new cards to blank, currently allows all faceUp stacks
    public void moveToBlank(Stack nextS) {
        ArrayList<Card> selectedStack = prevS.getSelected(this.prevC);
        prevS.removeCards(selectedStack, true);
        nextS.addToFaceUpStack(selectedStack);
    }

    //REQUIRES: this.nextC != null, this.nextS != null,
    //EFFECTS: verifies can only move card value of opposite suit color and 1 lower onto new stack
    private void checkMoveCards(Card prev, Stack prevS, Card next, Stack nextS) {
        ArrayList<Card> nextStack = nextS.getSelected(next);

        //3 conditions: prev is opposite suite, 1 value higher, and next card is index (0) of its stack.
        if ((prev.getCardValue() + 1 == next.getCardValue()) &&
                (nextStack.indexOf(next) == 0) &&
                checkOppositeSuit(prev, next)) {

            ArrayList<Card> selectedStack = prevS.getSelected(prev);
            prevS.removeCards(selectedStack, true);
            nextS.addToFaceUpStack(selectedStack);
        }
    }

    //EFFECTS: verifies suits of two cards are opposite
    private boolean checkOppositeSuit(Card c1, Card c2) {
        boolean c1Red = (c1.getSuit() == Suit.DIAMOND || c1.getSuit() == Suit.HEART) &&
                (c2.getSuit() == Suit.CLUB || c2.getSuit() == Suit.SPADE);
        boolean c1Black = (c2.getSuit() == Suit.DIAMOND || c2.getSuit() == Suit.HEART) &&
                (c1.getSuit() == Suit.CLUB || c1.getSuit() == Suit.SPADE);
        return (c1Red || c1Black);
    }














    //EFFECTS: Initiate 52 cards in starting deck
    private void makeDeck() {
        for (Suit suit : Suit.values()) {
            for (int i = 1; i <= 13; i++) {
                fullDeck.add(new Card(i, suit, false));
            }
        }
    }

    //EFFECTS: initializers
    private void initiateDeck() {
        shuffle(s1, 0, 1);
        shuffle(s2, 1, 1);
        shuffle(s3, 2, 1);
        shuffle(s4, 3, 1);
        shuffle(s5, 4, 1);
        shuffle(s6, 5, 1);
        shuffle(s7, 6, 1);

        shuffle(waste, 21, 3);

        decks.add(s1);
        decks.add(s2);
        decks.add(s3);
        decks.add(s4);
        decks.add(s5);
        decks.add(s6);
        decks.add(s7);
    }

    //EFFECTS: select amount of faceDown and faceUp cards to add to a deck
    private void shuffle(Stack into, int faceDown, int faceUp) {
        ArrayList<Card> faceDownCards = setFace(drawNumCards(faceDown), false);
        ArrayList<Card> faceUpCards = setFace(drawNumCards(faceUp), true);
        into.addCards(faceDownCards);
        into.addCards(faceUpCards);
    }

    //EFFECTS: converts entire list of given cards into faceUp or faceDown
    private ArrayList<Card> setFace(ArrayList<Card> cards, boolean faceUp) {
        for (Card card: cards) {
            if (faceUp) {
                card.setFaceUp();
            } else {
                card.setFaceDown();
            }
        }
        return cards;
    }

    //EFFECTS: draws randomized number of cards from full deck
    private ArrayList<Card> drawNumCards(int amount) {
        Collections.shuffle(fullDeck);

        ArrayList<Card> cards = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            Card card = fullDeck.get(0);
            fullDeck.remove(card);
            cards.add(card);
        }
        return cards;
    }
}
