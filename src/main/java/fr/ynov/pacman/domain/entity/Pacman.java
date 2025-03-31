package main.java.fr.ynov.pacman.domain.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

// Represents the Pac-Man player
public class Pacman extends Entity {
    private int lives = 3;
    private int score = 0;
    private int mouthAngle = 45;
    private boolean mouthClosing = true;
    private static final int MOUTH_ANIMATION_SPEED = 5;

    public Pacman(int x, int y, int speed) {
        super(x, y, speed);
    }

    @Override
    public void move() {
        switch (direction) {
            case UP -> y -= speed;
            case DOWN -> y += speed;
            case LEFT -> x -= speed;
            case RIGHT -> x += speed;
            case NONE -> {}
        }
        animateMouth();
    }

    public boolean isCollidingWithWall(Wall wall) {
        return this.x < wall.x + wall.width &&
               this.x + 20 > wall.x &&
               this.y < wall.y + wall.height &&
               this.y + 20 > wall.y;
    }

    private void animateMouth() {
        if (mouthClosing) {
            mouthAngle -= MOUTH_ANIMATION_SPEED;
            if (mouthAngle <= 15) mouthClosing = false;
        } else {
            mouthAngle += MOUTH_ANIMATION_SPEED;
            if (mouthAngle >= 45) mouthClosing = true;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);

        int startAngle = switch (direction) {
            case RIGHT -> 30;
            case UP -> 120;
            case LEFT -> 210;
            case DOWN -> 300;
            case NONE -> 30;
        };
        g.fillArc(x, y, 20, 20, startAngle, 360 - mouthAngle);
        drawEye(g);
    }

    private void drawEye(Graphics g) {
        g.setColor(Color.BLACK);
        int eyeX = x + 10 + (direction == Direction.LEFT ? -2 : direction == Direction.RIGHT ? 2 : 0);
        int eyeY = y + 6 + (direction == Direction.UP ? -2 : direction == Direction.DOWN ? 2 : 0);
        g.fillOval(eyeX, eyeY, 4, 4);
    }

    public void handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP -> setDirection(Direction.UP);
            case KeyEvent.VK_DOWN -> setDirection(Direction.DOWN);
            case KeyEvent.VK_LEFT -> setDirection(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> setDirection(Direction.RIGHT);
        }
    }

    public void eat(Coin coin) {
        score += 10;
    }

    public void eat(SuperCoin superCoin) {
        score += 50;
    }

    public void die() {
        lives--;
        if (lives > 0) {
            respawn();
        }
    }

    private void respawn() {
        x = 300;
        y = 300;
        direction = Direction.NONE;
    }

    // Getters and setters
    public Direction getDirection() { return direction; }
    public int getSpeed() { return speed; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public int getLives() { return lives; }
    public int getScore() { return score; }
    public boolean isAlive() { return lives > 0; }
}
