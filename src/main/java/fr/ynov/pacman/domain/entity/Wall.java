package main.java.fr.ynov.pacman.domain.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Wall {
    public int x;
    public int y;
    public int width;
    public int height;

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }
}
