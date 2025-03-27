package main.java.fr.ynov.pacman.gui;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow() {
        setTitle("Pacman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        GamePanel panel = new GamePanel();
        add(panel);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
