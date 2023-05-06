package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Card extends JPanel {
    private final int value;
    private final Suit suit;
    private boolean selected;
    private boolean isFaceUp;
    private Rectangle bound;

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

    //EFFECTS: when a card is put faceDown, it is also deselected
    public void setFaceDown() {
        this.isFaceUp = false;
        this.selected = false;
    }

    public void setBound(Rectangle bound) {
        this.bound = bound;
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

    //EFFECTS: Can only set select on faceUp card
    public void select() {
        if (!this.selected && this.isFaceUp) {
            this.selected = true;
        }
    }

    //EFFECTS: deselects Card
    public void deSelect() {
        if (this.selected) {
            this.selected = false;
        }
    }

    public boolean isSelected() {
        return this.selected;
    }

    // EFFECTS: return true if the given Point (x,y) is contained within the bounds of this Shape
    public boolean contains(Point point) {
        return this.bound.contains(point);
    }
}
