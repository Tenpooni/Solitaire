package Model;

public class Card {
    private final int value;
    private final Suit suit;

    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return this.value;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public String toString() {
        return this.stringConversion() + " of " + suit + "S";
    }

    private String stringConversion() {
        String str = "";
        if (this.value == 1) {
            str = "Ace";
        } else if (this.value == 11) {
            str = "Jack";
        } else if (this.value == 12) {
            str = "Queen";
        } else if (this.value == 13) {
            str = "King";
        } else {
            str = String.valueOf(this.value);
        }
        return str;
    }
}
