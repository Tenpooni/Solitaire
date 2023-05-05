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

    public String getValueString() {
        String str = "";
        if (this.value == 1) {
            str = "A";
        } else if (this.value == 11) {
            str = "J";
        } else if (this.value == 12) {
            str = "Q";
        } else if (this.value == 13) {
            str = "K";
        } else {
            str = String.valueOf(this.value);
        }
        return str;
    }

    public String getSuitString() {
        if (this.suit == Suit.HEART) {
            return "♥";
        } else if (this.suit == Suit.DIAMOND) {
            return "♦";
        } else if (this.suit == Suit.CLUB) {
            return "♣";
        } else {
            return "♠";
        }
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

    public void flip() {
        this.isFaceUp = !this.isFaceUp;
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
