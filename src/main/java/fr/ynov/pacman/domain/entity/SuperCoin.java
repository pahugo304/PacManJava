package main.java.fr.ynov.pacman.domain.entity;

import java.awt.Color;
import java.awt.Graphics;

// Represents a Super Coin (power pellet) that activates a special effect
public class SuperCoin extends Coin {
    private static final int SIZE = 12;
    private boolean activeEffect;

    public SuperCoin(int x, int y) {
        super(x, y);
        this.activeEffect = false;
    }

    @Override
    public void draw(Graphics g) {
        if (!isEaten()) {
            g.setColor(Color.WHITE);
            g.fillOval(getX() + 4, getY() + 4, SIZE, SIZE);
            g.setColor(Color.BLACK);
            g.drawOval(getX() + 4, getY() + 4, SIZE, SIZE);
        }
    }

    // Triggers the effect and marks it as eaten
    public void activateEffect() {
        this.activeEffect = true;
        setEaten(true);
    }

    public boolean isEffectActive() {
        return activeEffect;
    }
}
