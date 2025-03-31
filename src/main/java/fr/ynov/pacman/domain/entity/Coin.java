package main.java.fr.ynov.pacman.domain.entity;

import java.awt.Color;
import java.awt.Graphics;

// Coin entity eaten by Pac-Man
public class Coin extends Entity {
    private boolean isEaten;
    private static final int COIN_SIZE = 8;
    private static final int COIN_OFFSET = 6;

    public Coin(int x, int y) {
        super(x, y);
        this.isEaten = false;
    }

    @Override
    public void move() {
        // Coins don’t move
    }

    @Override
    public void draw(Graphics g) {
        if (!isEaten) {
            g.setColor(Color.ORANGE);
            g.fillOval(x + COIN_OFFSET, y + COIN_OFFSET, COIN_SIZE, COIN_SIZE);
        }
    }

    public boolean isEaten() {
        return isEaten;
    }

    public void setEaten(boolean eaten) {
        isEaten = eaten;
    }
}
