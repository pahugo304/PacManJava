package main.java.fr.ynov.pacman.domain.entity;

import java.awt.Color;
import java.awt.Graphics;

public class Wall {
    private int x, y, width, height;

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isColliding(Entity entity) {
        return entity.getX() < x + width &&
               entity.getX() + 20 > x &&
               entity.getY() < y + height &&
               entity.getY() + 20 > y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }
}
