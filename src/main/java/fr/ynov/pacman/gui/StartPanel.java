package main.java.fr.ynov.pacman.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;


public class StartPanel extends JPanel {
    public StartPanel(ActionListener startAction) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        
        // Title Label
        JLabel title = new JLabel("PAC-MAN");
        title.setFont(new Font("Arial", Font.BOLD, 70));
        title.setForeground(Color.YELLOW);
        
        // Play Button
        JButton playButton = new JButton("PLAY");
        playButton.setFont(new Font("Arial", Font.BOLD, 80));
        playButton.setBackground(Color.YELLOW);
        playButton.setForeground(Color.BLACK);
        playButton.setFocusPainted(false);
        playButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        playButton.addActionListener(startAction);
        
        // Ghost decorations
        JPanel ghostPanel = new JPanel(new FlowLayout());
        ghostPanel.setBackground(Color.BLACK);
        addGhostIcon(ghostPanel, Color.RED);
        addGhostIcon(ghostPanel, Color.PINK);
        addGhostIcon(ghostPanel, Color.CYAN);
        addGhostIcon(ghostPanel, Color.ORANGE);

        // Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(80, 0, 20, 0);
        
        add(title, gbc);
        add(ghostPanel, gbc);
        add(playButton, gbc);
        
        // Key listener for direct start
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    startAction.actionPerformed(
                        new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "start"));
                }
            }
        });
        setFocusable(true);
    }
    
    private void addGhostIcon(JPanel panel, Color color) {
        JLabel ghost = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(color);
                g.fillOval(0, 0, 30, 30); // Ghost body
                g.setColor(Color.WHITE);
                g.fillRect(0, 20, 30, 10); // Ghost bottom
            }
        };
        ghost.setPreferredSize(new Dimension(30, 30));
        panel.add(ghost);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Add Pac-Man dots pattern
        g.setColor(Color.YELLOW);
        for (int x = 50; x < getWidth(); x += 100) {
            for (int y = 300; y < getHeight() - 50; y += 100) {
                g.fillOval(x, y, 6, 6);
            }
        }
    }
}
