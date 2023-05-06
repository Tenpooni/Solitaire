package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Solitaire {

    //Full Deck to draw cards from
    private final ArrayList<Card> fullDeck = new ArrayList<>();
    //List of Interactable decks
    private ArrayList<Stack> decks = new ArrayList<>();

    private final Stack s1;
    private final Stack s2;
    private final Stack s3;
    private final Stack s4;
    private final Stack s5;
    private final Stack s6;
    private final Stack s7;

    public Solitaire() {
        this.s1 = new Stack("S1");
        this.s2 = new Stack("S2");
        this.s3 = new Stack("S3");
        this.s4 = new Stack("S4");
        this.s5 = new Stack("S5");
        this.s6 = new Stack("S6");
        this.s7 = new Stack("S7");
        makeDeck();
        initiateDeck();
    }

    public ArrayList<Stack> getDecks() {
        return decks;
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
            Card card = fullDeck.get(i);
            fullDeck.remove(card);
            cards.add(card);
        }

        return cards;
    }
}
