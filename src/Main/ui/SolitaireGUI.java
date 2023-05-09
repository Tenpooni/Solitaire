package ui;

import Model.Card;
import Model.Deck;
import Model.Solitaire;
import Model.Stack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SolitaireGUI extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private DeckGUI currentDeckGUI;
    private Solitaire solitaire;


    public SolitaireGUI() {
        super("Solitaire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        solitaire = new Solitaire();

        initializeGraphics();
        initializeInteraction();
    }

    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    // EFFECTS:  initializes a MouseListener to be used in the JFrame
    private void initializeInteraction() {
        Click gml = new Click();
        addMouseListener(gml);
    }

    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addDeckGUI();

        pack();
        centreOnScreen();
        setVisible(true);
    }

    private void addDeckGUI() {
        DeckGUI newDeckGUI = new DeckGUI(this.solitaire);
        currentDeckGUI = newDeckGUI;
        add(newDeckGUI, BorderLayout.CENTER);
        validate();
    }


    //TODO: Add foundation deck handling to mouseclick event
    //REQUIRES: selected card must be faceUp
    //EFFECTS: When a card is clicked, clear previous selection, make new card selected.
    private void handleMouseClick(MouseEvent e) {
        //Stack nextS = getStackAtMouse(e.getPoint());
        Deck nextS = getDeckAtMouse(e.getPoint());
        Card nextC = getCardAtMouse(e.getPoint());

        //solitaire.deselectAll();
        if (nextC == null) {
            solitaire.deselectAll();
        }

        if (nextC != null) {
            solitaire.setSelection(nextC, nextS);
        } else if (nextS != null && nextS.isEmpty()) {
            solitaire.checkMoveBlank(nextS);
        }
        repaint();
    }

    //MouseAdapter class
    private class Click extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            handleMouseClick(translateEvent(e));
        }

        private MouseEvent translateEvent(MouseEvent e) {
            return SwingUtilities.convertMouseEvent(e.getComponent(), e, currentDeckGUI);
        }
    }

    public Card getCardAtMouse(Point point) {
        return currentDeckGUI.getCardAtPoint(point);
    }

    //TODO: not being used, to delete?
//    public Stack getStackAtMouse(Point point) {
//        return currentDeckGUI.getStackAtPoint(point);
//    }

    //TODO: TEST METHOD WITH ABSTRACT DECK
    public Deck getDeckAtMouse(Point p) {
        return currentDeckGUI.getDeckAtPoint(p);
    }

}
