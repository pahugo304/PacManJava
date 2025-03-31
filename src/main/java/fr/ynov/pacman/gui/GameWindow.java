package main.java.fr.ynov.pacman.gui;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
   private static final int WINDOW_WIDTH = 800;
   private static final int WINDOW_HEIGHT = 800;

   public GameWindow() {
       setTitle("PAC-MAN");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
       
       // Start with menu
       showStartMenu();
       
       pack();
       setLocationRelativeTo(null);
       setVisible(true);
   }

   private void showStartMenu() {
       // Clear current components and display the start menu
       getContentPane().removeAll();
       add(new StartPanel(e -> startGame()));
       revalidate();
   }

   private void startGame() {
       // Clear current components and initialize game
       getContentPane().removeAll();
       GamePanel gamePanel = new GamePanel(WINDOW_WIDTH, WINDOW_HEIGHT);
       add(gamePanel);
       gamePanel.requestFocusInWindow();
       revalidate();
   }
}
