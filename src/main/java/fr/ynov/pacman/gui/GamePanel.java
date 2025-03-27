package main.java.fr.ynov.pacman.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import main.java.fr.ynov.pacman.domain.game.Game;

public class GamePanel extends JPanel implements ActionListener {
    private final Game game;
    private final Timer timer;

    public GamePanel() {
        game = new Game(600, 600);
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);

        timer = new Timer(16, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                game.handleInput(e.getKeyCode());
                requestFocus();
            }
        });
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.update();
        repaint();
    }
}
