package Model;

import java.util.ArrayList;
import java.util.Random;

public class Solitaire {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    //full Deck to draw cards from
    private final ArrayList<Card> fullDeck = new ArrayList<>();

    private final Deck active;

    public Solitaire() {
        this.active = new Deck("ACTIVE");
        makeFullDeck();
        shuffle(active, 5, 3);

        printStack(active);
    }

    //EFFECTS: Initiate 52 cards in starting deck
    private void makeFullDeck() {
        for (Suit suit : Suit.values()) {
            for (int i = 1; i <= 13; i++) {
                fullDeck.add(new Card(i, suit, false));
            }
        }
    }

    //EFFECTS: select amount of faceDown and faceUp cards to add to a deck
    private void shuffle(Deck into, int faceDown, int faceUp) {
        ArrayList<Card> faceDownCards = setFace(getNumCards(faceDown), false);
        ArrayList<Card> faceUpCards = setFace(getNumCards(faceUp), true);
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
    private ArrayList<Card> getNumCards(int amount) {
        ArrayList<Card> cards = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < amount; i++) {
            int randInt = rand.nextInt(fullDeck.size());
            Card card = fullDeck.get(randInt);
            fullDeck.remove(card);
            cards.add(card);
        }

        return cards;
    }


    //print to Console
    private void printStack(Deck d) {
        System.out.println(d.getName());
        for (Card c : d.getCards()) {
            System.out.println(c.toString());
        }
    }

}
