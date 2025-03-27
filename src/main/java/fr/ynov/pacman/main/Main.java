package main.java.fr.ynov.pacman.main;

import main.java.fr.ynov.pacman.gui.GameWindow;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameWindow();
        });
    }
}
