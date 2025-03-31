package main.java.fr.ynov.pacman.domain.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

// Wall object for maze boundaries
public class Wall {
    public int x, y, width, height;  // Position and dimensions

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Returns collision bounds
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Draws blue wall rectangle
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }
}
