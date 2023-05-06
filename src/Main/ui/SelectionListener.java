package ui;

import Model.Solitaire;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class SelectionListener extends JPanel implements MouseListener {

    private Solitaire solitaire;
    private MouseListener ml;
    private JPanel test;

    public SelectionListener(Solitaire s, int width, int height) {
        super();
        this.solitaire = s;
        this.test = new JPanel();
        addMouseListener(this);
        setSize(width,height);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("TEST MOUSE LISTENER");
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
