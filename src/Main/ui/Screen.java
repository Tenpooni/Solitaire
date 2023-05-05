package ui;

import Model.Solitaire;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen extends JPanel{

    private Solitaire solitaire;

    public Screen(Solitaire s) {
        super();
        this.solitaire = s;
    }


    //TODO: Make sure this doesn't conflict with SolitaireGUI
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


    }
}
