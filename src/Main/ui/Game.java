package ui;

import Model.Stack;
import Model.Card;
import Model.Suit;

public class Game {

    private Stack deck;
    private Stack draw;

    public Game() {
        this.deck = new Stack("Deck");
        this.draw = new Stack("Draw");
        makeDeck();
        printStack(deck);
    }

    private void makeDeck() {
        for (Suit suit : Suit.values()) {
            for (int i = 1; i <= 13; i++) {
                deck.addFaceDown(new Card(i, suit));
            }
        }
    }

    private void printStack(Stack s) {
        System.out.println(s.getName());
        for (Card c : s.getFaceDownCards()) {
            System.out.println(c.toString());
        }
    }
}
