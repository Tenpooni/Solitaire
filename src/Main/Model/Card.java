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

    private final int CARDWIDTH = 70;
    private final int CARDHEIGHT = 100;

    public Card(int value, Suit suit, boolean isFaceUp) {
        this.value = value;
        this.suit = suit;
        this.selected = false;
        this.isFaceUp = isFaceUp;
        this.bound = bound;
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

    public void setBound(Rectangle bound) {
        this.bound = bound;
    }

    //TODO: remove if unneeded
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


    public void select(MouseEvent e) {
        if (!this.selected) {
            this.selected = true;
        }
    }

    public void deSelect() {
        if (this.selected) {
            this.selected = false;
        }
    }

    public boolean isSelected() {
        return this.selected;
    }





    //FIX THESE METHODS
    // EFFECTS: return true if the given Point (x,y) is contained within the bounds of this Shape
    public boolean contains(Point point) {
        //int point_x = point.x;
        //int point_y = point.y;
        return this.bound.contains(point);
    }
}
