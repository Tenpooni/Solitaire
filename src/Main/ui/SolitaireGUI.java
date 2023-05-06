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

    private boolean isSelecting = false;

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

    // EFFECTS:  initializes a MouseListener to be used in the JFrame
    private void initializeInteraction() {
        Click gml = new Click();
        addMouseListener(gml);
    }




    private void handleMouseClick(MouseEvent e) {
        //THIS IS NOT
        Card c = getCardAtMouse(e.getPoint());
        if (c != null) {
            System.out.println(c.toString() + " clicked");
        }

        //THIS IS WORKING
        if (isSelecting) {
            System.out.println("isSelecting");
        } else {
            System.out.println("isNotSelecting");
        }
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
