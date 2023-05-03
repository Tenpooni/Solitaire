package ui;

import Model.Solitaire;

import javax.swing.*;
import java.awt.*;

public class SolitaireGUI extends JFrame {

    private Solitaire solitaire;
    private Screen screen;

    public SolitaireGUI() {
        super("Solitaire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        solitaire = new Solitaire();
        screen = new Screen(solitaire);

        add(screen);

        pack();
        centreOnScreen();
        setVisible(true);
    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

}
