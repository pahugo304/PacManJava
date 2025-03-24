package main.java.fr.ynov.pacman.domain.entity;

import java.awt.Color;
import java.awt.Graphics;

public class Coin {
    private int x;
    private int y;
    private boolean isEaten;

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
        this.isEaten = false;
    }

    public boolean isEaten() {
        return isEaten;
    }

    public void setEaten(boolean eaten) {
        this.isEaten = eaten;
    }

    public void draw(Graphics g) {
        if (!isEaten) {
            g.setColor(Color.ORANGE);
            g.fillOval(x + 6, y + 6, 8, 8);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
