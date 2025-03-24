package main.java.fr.ynov.pacman.domain.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Ghost extends Enemy {
    private Color color;
    private Pacman target;
    private int scatterTime;
    private int chaseTime;
    private int timer = 0;

    public Ghost(int x, int y, int speed, Color color, Pacman target) {
        super(x, y, speed);
        this.color = color;
        this.target = target;
        this.scatterTime = 5;
        this.chaseTime = 10;
    }

    public void update() {
        timer++;
    
        if (state == EnemyState.SCATTER && timer >= scatterTime * 60) {
            changeState(EnemyState.CHASE);
            timer = 0;
        } else if (state == EnemyState.CHASE && timer >= chaseTime * 60) {
            changeState(EnemyState.SCATTER);
            timer = 0;
        }
    }

    @Override
    public void changeState(EnemyState newState) {
        this.state = newState;
    }

    @Override
    public void chase(Pacman target) {
        if (state == EnemyState.CHASE) {
            if (x < target.getX()) x += speed;
            if (x > target.getX()) x -= speed;
            if (y < target.getY()) y += speed;
            if (y > target.getY()) y -= speed;
        }
    }

    public void scatter() {
        if (state == EnemyState.SCATTER) {
            x += (Math.random() > 0.5 ? speed : -speed);
            y += (Math.random() > 0.5 ? speed : -speed);
        }
    }

    public void decideNextMove() {
        if (state == EnemyState.FRIGHTENED) {
            x += (Math.random() > 0.5 ? -speed : speed);
            y += (Math.random() > 0.5 ? -speed : speed);
        }
    }

    public boolean isCollidingWithWall(List<Wall> walls) {
        for (Wall wall : walls) {
            if (wall.isColliding(this)) return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 20, 20);
    }

    @Override
    public void move() {
        if (state == EnemyState.CHASE) {
            chase(target);
        } else if (state == EnemyState.SCATTER) {
            scatter();
        } else if (state == EnemyState.FRIGHTENED) {
            decideNextMove();
        }
    }
}
