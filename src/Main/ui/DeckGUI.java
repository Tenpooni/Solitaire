package ui;

import Model.Card;
import Model.Solitaire;
import Model.Stack;
import Model.Suit;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class DeckGUI extends JPanel {

    private Solitaire solitaire;
    private final int CARDWIDTH = 70;
    private final int CARDHEIGHT = 100;
    private final int OFFSET = 20;

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
        Collection<Card> cards = stack.viewAllCards();

        int deltaY = OFFSET;

        for (Card c : cards) {
            printCard(g2d, c, xPos, yPos, deltaY);
            deltaY += OFFSET;
        }
    }

    //EFFECTS: prints both faceUp or faceDown card
    private void printCard(Graphics2D g2d, Card c, int xPos, int yPos, int deltaY) {
        Graphics2D cardGraphic = (Graphics2D) g2d.create();
        String cardText = getCardText(c);
        //Color textColor = getColor(c);
        if (c.getFaceUp()) {
            paintFaceUpCard(g2d, cardText, xPos, yPos + deltaY);
        } else {
            paintFaceDownCard(g2d, xPos, yPos + deltaY);
        }
        cardGraphic.dispose();
    }

    //EFFECTS: draws individual faceDown card
    private void paintFaceDownCard(Graphics2D g2d, int xPos, int yPos) {
        Rectangle bounds = new Rectangle(xPos, yPos, CARDWIDTH , CARDHEIGHT);
        g2d.setColor(Color.GRAY);
        g2d.fill(bounds);
        g2d.setColor(Color.BLACK);
        g2d.draw(bounds);
    }


    //EFFECTS: draws individual faceUp card
    private void paintFaceUpCard(Graphics2D g2d,String text, int xPos, int yPos) {
        Rectangle bounds = new Rectangle(xPos, yPos, CARDWIDTH , CARDHEIGHT);
        g2d.setColor(Color.WHITE);
        g2d.fill(bounds);
        g2d.setColor(Color.BLACK);
        g2d.draw(bounds);

        Graphics2D topLeftText = (Graphics2D) g2d.create();
        paintCardText(topLeftText, bounds, text, 5, 5);
        topLeftText.dispose();

        Graphics2D bottomRightText = (Graphics2D) g2d.create();
        paintCardText(bottomRightText, bounds, text, CARDWIDTH - (CARDWIDTH / 3), CARDHEIGHT - (CARDHEIGHT / 5));
        bottomRightText.dispose();
    }

    //EFFECTS: Returns suit and value of card as string
    private String getCardText(Card c) {
        return c.getValueString() + c.getSuitString();
    }

    private Color getColor(Card c) {
        if (c.getSuit() == Suit.DIAMOND || c.getSuit() == Suit.HEART) {
            return Color.RED;
        } else {
            return Color.BLACK;
        }
    }

    //EFFECTS: Draws on card text
    private void paintCardText(Graphics2D g2d, Rectangle bounds, String text, int xPad, int yPad) {
        FontMetrics fm = g2d.getFontMetrics();

        g2d.translate(bounds.x + xPad, bounds.y + yPad);
        g2d.setClip(0, 0, bounds.width - xPad, bounds.height - yPad);

        g2d.drawString(text, 0, fm.getAscent());
    }

}
