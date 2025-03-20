package main.java.fr.ynov.pacman.domain.entity;

import java.awt.Graphics;
import java.util.List;

public abstract class Pacman extends Entity
{
    public Pacman(int x, int y, int speed) {
        super(x, y, speed);
    }

    @Override
    public abstract void move();
    @Override
    public abstract void draw(Graphics g);
    
    public boolean isCollidingWithWall(List<Wall> walls) {
        for (Wall wall : walls) {
            if (wall.isColliding(this)) return true;
        }
        return false;
    }
}
