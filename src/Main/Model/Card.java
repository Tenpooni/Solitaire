package Model;

public class Card {
    private final int value;
    private final Suit suit;
    private boolean selected;
    private boolean isFaceUp;

    public Card(int value, Suit suit, boolean isFaceUp) {
        this.value = value;
        this.suit = suit;
        this.selected = false;
        this.isFaceUp = isFaceUp;
    }

    public int getValue() {
        return this.value;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public boolean getFaceUp() {
        return this.isFaceUp;
    }

    public void setFaceUp() {
        this.isFaceUp = true;
    }

    public void setFaceDown() {
        this.isFaceUp = false;
    }

    public String toString() {
        return this.stringConversion() + " of " + suit + "S" + " : FaceUp " + isFaceUp;
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
