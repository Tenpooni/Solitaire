package ui;

import Model.Solitaire;

import javax.swing.*;
import java.awt.*;

public class SolitaireGUI extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Solitaire solitaire;
    private Screen screen;

    public SolitaireGUI() {
        super("Solitaire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        solitaire = new Solitaire();

        initializeGraphics();
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
        add(newDeckGUI, BorderLayout.CENTER);
        validate();
    }

    //TODO: add text UI panel
    private void addScreen() {
        Screen newScreen = new Screen(this.solitaire);
        add(newScreen, BorderLayout.SOUTH);
        validate();
    }
}
