package ui;

import Model.Card;
import Model.Solitaire;
import Model.Stack;
import Model.Suit;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class DeckGUI extends JPanel {

    private final Solitaire solitaire;
    private final int CARDWIDTH = 70;
    private final int CARDHEIGHT = 100;
    private final int cardOFFSET = 20;
    private final int stackOFFSET = 70;

    public DeckGUI(Solitaire s) {
        super();
        this.solitaire = s;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.GREEN.darker().darker().darker());

        Graphics2D g2d = (Graphics2D) g.create();
        printStack(g2d, this.solitaire.getActive(), 150, 50);
    }

    //EFFECTS: prints entire stack, each next card is OFFSET distance down
    private void printStack(Graphics2D g2d, Stack stack, int xPos, int yPos) {
        Collection<Card> cards = stack.viewReverseCards();
        int deltaY = cardOFFSET;

        //Print card backing
        printStackBacking(g2d, xPos, yPos + deltaY);
        //drawCardFrame(g2d, Color.GREEN.darker().darker().darker().darker(), Color.BLACK, xPos, yPos + deltaY);

        for (Card c : cards) {
            printCard(g2d, c, xPos, yPos, deltaY);
            deltaY += cardOFFSET;
        }
    }

    //EFFECTS: prints either faceUp or faceDown card
    private void printCard(Graphics2D g2d, Card c, int xPos, int yPos, int deltaY) {
        Graphics2D cardGraphic = (Graphics2D) g2d.create();
        String cardText = getCardText(c);
        Color textColor = getColor(c);
        if (c.getFaceUp()) {
            paintFaceUpCard(g2d, cardText, textColor, xPos, yPos + deltaY);
        } else {
            paintFaceDownCard(g2d, xPos, yPos + deltaY);

        }
        cardGraphic.dispose();
    }

    //EFFECTS: helper function for drawing card base
    private void drawCardBase(Graphics2D g2d, Rectangle bounds, Color fill, Color border, int xPos, int yPos) {
        g2d.setColor(fill);
        g2d.fill(bounds);
        g2d.setColor(border);
        g2d.draw(bounds);
    }

    //EFFECTS: draws blank for stack with no cards
    private void printStackBacking(Graphics2D g2d, int xPos, int yPos) {
        Rectangle bounds = new Rectangle(xPos, yPos, CARDWIDTH, CARDHEIGHT);
        drawCardBase(g2d, bounds, Color.GREEN.darker().darker().darker().darker(), Color.BLACK, xPos, yPos);
    }

    //EFFECTS: draws individual faceDown card
    private void paintFaceDownCard(Graphics2D g2d, int xPos, int yPos) {
        Rectangle bounds = new Rectangle(xPos, yPos, CARDWIDTH, CARDHEIGHT);
        drawCardBase(g2d, bounds, Color.GRAY, Color.BLACK, xPos, yPos);
    }

    //EFFECTS: draws individual faceUp card
    private void paintFaceUpCard(Graphics2D g2d, String text, Color textColor, int xPos, int yPos) {
        Rectangle bounds = new Rectangle(xPos, yPos, CARDWIDTH , CARDHEIGHT);
        drawCardBase(g2d, bounds, Color.WHITE, Color.BLACK, xPos, yPos);

        Graphics2D topLeftText = (Graphics2D) g2d.create();
        paintCardText(topLeftText, bounds, text, textColor, 5, 5);
        topLeftText.dispose();

        Graphics2D bottomRightText = (Graphics2D) g2d.create();
        paintCardText(bottomRightText, bounds, text, textColor, CARDWIDTH - (CARDWIDTH / 3), CARDHEIGHT - (CARDHEIGHT / 5));
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

    //EFFECTS: Draws on card text
    private void paintCardText(Graphics2D g2d, Rectangle bounds, String text, Color textColor, int xPad, int yPad) {
        FontMetrics fm = g2d.getFontMetrics();

        g2d.translate(bounds.x + xPad, bounds.y + yPad);
        g2d.setClip(0, 0, bounds.width - xPad, bounds.height - yPad);
        g2d.setColor(textColor);
        g2d.drawString(text, 0, fm.getAscent());
    }

}
