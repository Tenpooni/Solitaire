package ui;

import Model.Card;
import Model.Solitaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SolitaireGUI extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Card selection;

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
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
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




    //REQUIRES: selected card must be faceUp
    //EFFECTS: When a card is clicked, clear previous selection, make new card selected.
    //TODO: CHANGE METHODS FOR CLICK OTHER DECK INTERACTIONS
    private void handleMouseClick(MouseEvent e) {
        Card c = getCardAtMouse(e.getPoint());

        if (c != null) {
            if (selection != null) {
                selection.deSelect();
            }
            selection = c;
            selection.select();
        } else {
            if (selection != null) {
                selection.deSelect();
            }
        }
        repaint();
    }


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

}
