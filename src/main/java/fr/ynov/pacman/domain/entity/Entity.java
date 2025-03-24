package main.java.fr.ynov.pacman.domain.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int speed;
    protected Direction direction;
    protected final int size = 20;

    public Entity(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = Direction.NONE;
    }

    public abstract void move();
    public abstract void draw(Graphics g);

    public boolean isColliding(Entity other) {
        return getBounds().intersects(other.getBounds());
    }

    public boolean isCollidingWithWall(Wall wall) {
        return getBounds().intersects(wall.getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Direction getDirection() { return direction; }
    public int getSpeed() { return speed; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setDirection(Direction direction) { this.direction = direction; }
}
