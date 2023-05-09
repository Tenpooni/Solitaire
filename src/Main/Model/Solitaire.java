package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Solitaire {

    //Full Deck to draw cards from
    private final ArrayList<Card> fullDeck = new ArrayList<>();

    //List of Interactive decks
    private final ArrayList<Stack> stacks = new ArrayList<>();
    private final ArrayList<Foundation> foundations = new ArrayList<>();


    private Card prevC;
    private Deck prevS;

    private final Stack s1;
    private final Stack s2;
    private final Stack s3;
    private final Stack s4;
    private final Stack s5;
    private final Stack s6;
    private final Stack s7;

    //TODO: FIX WASTE
    private final Waste waste;
    private final Foundation f1;
    private final Foundation f2;
    private final Foundation f3;
    private final Foundation f4;


    public Solitaire() {
        this.s1 = new Stack("S1");
        this.s2 = new Stack("S2");
        this.s3 = new Stack("S3");
        this.s4 = new Stack("S4");
        this.s5 = new Stack("S5");
        this.s6 = new Stack("S6");
        this.s7 = new Stack("S7");
        this.f1 = new Foundation("F1");
        this.f2 = new Foundation("F2");
        this.f3 = new Foundation("F3");
        this.f4 = new Foundation("F4");
        this.waste = new Waste("Waste");

        makeDeck();
        initiateDeck();
    }

    public ArrayList<Deck> getAllDecks() {
        ArrayList<Deck> allDeck = new ArrayList<>(stacks);
        allDeck.add(this.waste);
        allDeck.addAll(foundations);
        return allDeck;
    }

    public ArrayList<Stack> getPlayingDecks() {
        return stacks;
    }

    public Waste getWasteDeck() {
        return waste;
    }

    public ArrayList<Foundation> getFoundationDecks() {
        return foundations;
    }

    //EFFECTS: clears current selected card
    public void deselectAll() {
        if (this.prevC != null) {
            this.prevC.deSelect();

            System.out.println("deselected");
        }
    }

    //EFFECTS: checks if current selection (Card) exists,
    //if next selection is valid then move selected stack, otherwise set as next selection
    public void setSelection(Card nextC, Deck nextS) {
        if (this.prevC != null) {
            checkMoveCards(nextC, nextS);
        }

        //TODO: FIX THIS this for waste deck
        if (nextS instanceof Waste) {
            flipWasteDeck(nextS);
        }

        nextC.select();
        this.prevC = nextC;
        this.prevS = nextS;
    }

    //TODO: CALL THIS METHOD TO FLIP WASTE DECK
    public void flipWasteDeck(Deck nextD) {
        ArrayList<Card> faceUps = new ArrayList<>(getWasteDeck().faceUp);
        ((Waste) nextD).setAllFaceDown();
        nextD.drawNewFaceUp();
    }

    public void checkMoveBlank(Deck nextS) {
        //3 conditions to move to blank foundation space: is Foundation instance,
        // card selected is value 1, card selected is index 0 (printed bottom up) from previous Deck
        if (nextS instanceof Stack) {
            moveToDeck(nextS);
        } else if (nextS instanceof Foundation) {
            moveToBlankFoundation(nextS);
        }
        deselectAll();
    }

    //REQUIRES: this.nextC != null, this.nextS != null,
    //EFFECTS: verifies can only move card value of opposite suit color and 1 lower onto new stack
    private void checkMoveCards(Card next, Deck nextS) {
        if (nextS instanceof Stack) {
            moveToStack(next, nextS);
        } else if (nextS instanceof Foundation) {
            moveToFoundation(next, nextS);
        }
        deselectAll();
    }

    //TODO: moveToBlankFoundation method, how to remove null check?
    private void moveToBlankFoundation(Deck nextS) {
        //2 conditions: card moving in is value 1, position of card moving in is index 0 if from a selected stack
        if ((this.prevC.getCardValue() == 1) &&
                (prevS.getSelected(this.prevC) != null) &&
                (prevS.getSelected(this.prevC).get(0).equals(this.prevC))) {
            moveToDeck(nextS);
        }
    }

    //EFFECTS: move method if moving cards into non-empty Stack
    private void moveToStack(Card next, Deck nextS) {
        //4 conditions: prev is selected, opposite suit, 1 value higher, and next card is index (0) of its selected stack.
        if ((this.prevC.getCardValue() + 1 == next.getCardValue()) &&
                (this.prevC.isSelected()) &&
                (nextS.getSelected(next).indexOf(next) == 0) &&
                checkOppositeSuit(this.prevC, next)) {
            moveToDeck(nextS);
        }
    }

    //HELPER TO CHECKSTACK
    //EFFECTS: verifies suits of two cards are opposite
    private boolean checkOppositeSuit(Card c1, Card c2) {
        boolean c1Red = (c1.getSuit() == Suit.DIAMOND || c1.getSuit() == Suit.HEART) &&
                (c2.getSuit() == Suit.CLUB || c2.getSuit() == Suit.SPADE);
        boolean c1Black = (c2.getSuit() == Suit.DIAMOND || c2.getSuit() == Suit.HEART) &&
                (c1.getSuit() == Suit.CLUB || c1.getSuit() == Suit.SPADE);
        return (c1Red || c1Black);
    }

    private void moveToFoundation(Card next, Deck nextS) {
        //2 conditions: card being added is 1 value higher and shares same suit.
        if ((this.prevC.getCardValue() - 1 == next.getCardValue()) &&
                (this.prevC.getSuit().equals(next.getSuit()))) {
            moveToDeck(nextS);
        }
    }

    private void moveToDeck(Deck nextS) {
        ArrayList<Card> selectedStack = this.prevS.getSelected(this.prevC);
        this.prevS.removeFaceUpCards(selectedStack);
        this.prevS.refreshCards();
        nextS.addToFaceUpCards(selectedStack);
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

        stacks.add(s1);
        stacks.add(s2);
        stacks.add(s3);
        stacks.add(s4);
        stacks.add(s5);
        stacks.add(s6);
        stacks.add(s7);
        foundations.add(f1);
        foundations.add(f2);
        foundations.add(f3);
        foundations.add(f4);
    }

    //EFFECTS: select amount of faceDown and faceUp cards to add to a deck
    private void shuffle(Deck into, int faceDown, int faceUp) {
        ArrayList<Card> faceDownCards = setFace(drawNumCards(faceDown), false);
        ArrayList<Card> faceUpCards = setFace(drawNumCards(faceUp), true);
        into.addToFaceDownCards(faceDownCards);
        into.addToFaceUpCards(faceUpCards);
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
