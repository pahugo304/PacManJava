package main.java.fr.ynov.pacman.domain.entity;

import java.awt.Graphics;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int speed;
    protected Direction direction;

    public Entity(int x, int y) {
        this(x, y, 0);
    }

    public Entity(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = Direction.NONE;
    }

    public abstract void move();
    public abstract void draw(Graphics g);

    public boolean isColliding(Entity other) {
        return Math.abs(x - other.x) < 20 && Math.abs(y - other.y) < 20;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getSpeed() { return speed; }
    public Direction getDirection() { return direction; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setDirection(Direction direction) { this.direction = direction; }
}
