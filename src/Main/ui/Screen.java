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
        setPreferredSize(new Dimension(solitaire.WIDTH, solitaire.HEIGHT));
        setBackground(Color.GREEN.darker().darker().darker());
        this.solitaire = s;
    }
}
