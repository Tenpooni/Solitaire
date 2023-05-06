package ui;

import Model.Card;
import Model.Solitaire;
import Model.Stack;
import Model.Suit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class DeckGUI extends JPanel {

    private final Solitaire solitaire;
    private ArrayList<Stack> decks;
    private final int CARDWIDTH = 70;
    private final int CARDHEIGHT = 100;
    private final int cardOFFSET = 20;
    private final int stackOFFSET = 35;


    public DeckGUI(Solitaire s) {
        super();
        this.solitaire = s;
        this.decks = solitaire.getDecks();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.GREEN.darker().darker().darker());

        Graphics2D g2d = (Graphics2D) g.create();

        int deltaX = 0;
        for (Stack s : decks) {
            printStack(g2d, s, stackOFFSET + deltaX, 100);
            deltaX += (stackOFFSET + CARDWIDTH);
        }
    }

    //EFFECTS: prints entire stack, each next card is cardOFFSET distance below the next
    private void printStack(Graphics2D g2d, Stack stack, int xPos, int yPos) {
        Collection<Card> cards = stack.viewReverseCards();
        int deltaY = cardOFFSET;

        //Print card backing
        printStackBacking(g2d, xPos, yPos + deltaY);

        for (Card c : cards) {
            printCard(g2d, c, xPos, yPos + deltaY);
            c.setBound(new Rectangle(xPos, yPos + deltaY, CARDWIDTH, CARDHEIGHT));
            deltaY += cardOFFSET;
        }
    }

    //EFFECTS: prints either faceUp or faceDown card
    private void printCard(Graphics2D g2d, Card c, int xPos, int yPos) {
        Graphics2D cardGraphic = (Graphics2D) g2d.create();
        String cardText = getCardText(c);
        Color textColor = getColor(c);
        if (c.getFaceUp()) {
            paintFaceUpCard(g2d, cardText, textColor, xPos, yPos, c.isSelected());
        } else {
            paintFaceDownCard(g2d, xPos, yPos);
        }
        cardGraphic.dispose();
    }

    //EFFECTS: helper function for drawing card base, border is thicker/orange if the card is currently selected
    private void drawCardBase(Graphics2D g2d, Rectangle bounds, Color fill, Color border, boolean isSelected) {
        Stroke defaultStroke = g2d.getStroke();
        g2d.setColor(fill);
        g2d.fill(bounds);
        if (isSelected) {
            border = Color.ORANGE;
            g2d.setStroke(new BasicStroke(3));
        }
        g2d.setColor(border);
        g2d.draw(bounds);
        g2d.setStroke(defaultStroke);
    }

    //EFFECTS: draws blank for stack with no cards
    private void printStackBacking(Graphics2D g2d, int xPos, int yPos) {
        Rectangle bounds = new Rectangle(xPos, yPos, CARDWIDTH, CARDHEIGHT);
        drawCardBase(g2d, bounds, Color.GREEN.darker().darker().darker().darker(), Color.BLACK, false);
    }

    //EFFECTS: draws individual faceDown card
    private void paintFaceDownCard(Graphics2D g2d, int xPos, int yPos) {
        Rectangle bounds = new Rectangle(xPos, yPos, CARDWIDTH, CARDHEIGHT);
        drawCardBase(g2d, bounds, Color.GRAY, Color.BLACK, false);
    }

    //EFFECTS: draws individual faceUp card
    private void paintFaceUpCard(Graphics2D g2d, String text, Color textColor, int xPos, int yPos, boolean isSelected) {
        Rectangle bounds = new Rectangle(xPos, yPos, CARDWIDTH , CARDHEIGHT);

        drawCardBase(g2d, bounds, Color.WHITE, Color.BLACK, isSelected);

        Graphics2D topLeftText = (Graphics2D) g2d.create();
        paintCardText(topLeftText, bounds, text, textColor, 6, 5);
        topLeftText.dispose();

        Graphics2D bottomRightText = (Graphics2D) g2d.create();
        paintCardText(bottomRightText, bounds, text, textColor,
                CARDWIDTH - (CARDWIDTH * 2 / 5), CARDHEIGHT - (CARDHEIGHT / 5));
        bottomRightText.dispose();
    }

    //EFFECTS: Returns suit and value of card as string
    private String getCardText(Card c) {
        return c.getValueString() + c.getSuitString();
    }

    //EFFECTS: Returns color to use for card Red/Black
    private Color getColor(Card c) {
        if (c.getSuit() == Suit.DIAMOND || c.getSuit() == Suit.HEART) {
            return Color.RED;
        } else {
            return Color.BLACK;
        }
    }

    //EFFECTS: Draws card text
    private void paintCardText(Graphics2D g2d, Rectangle bounds, String text, Color textColor, int xPad, int yPad) {
        FontMetrics fm = g2d.getFontMetrics();

        g2d.translate(bounds.x + xPad, bounds.y + yPad);
        g2d.setClip(0, 0, bounds.width - xPad, bounds.height - yPad);
        g2d.setColor(textColor);
        g2d.drawString(text, 0, fm.getAscent());
    }


    //EFFECTS: returns card if point clicked contains one, null otherwise
    public Card getCardAtPoint(Point point) {
        for (Stack s : solitaire.getDecks()) {
            for (Card c : s.viewAllCards()) {
                if (c.contains(point)) {
                    return c;
                }
            }
        }
        return null;
    }

    //EFFECTS: returns stack containing the card if point clicked contains card, null otherwise
    public Stack getStackAtPoint(Point point) {
        for (Stack s : solitaire.getDecks()) {
            for (Card c : s.viewAllCards()) {
                if (c.contains(point)) {
                    return s;
                }
            }
        }
        return null;
    }

}
