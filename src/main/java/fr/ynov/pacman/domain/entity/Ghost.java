package main.java.fr.ynov.pacman.domain.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.awt.Rectangle;

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

    public void changeBehavior() {
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


    public void resetToStart() {
        this.direction = Direction.LEFT;
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

    public EnemyState getState() {
        return this.state;
    }    

    public void die() {
        this.x = 50;
        this.y = 50;
        this.state = EnemyState.CHASE;
    }

    public void scatter() {
        if (state == EnemyState.SCATTER) {
            x += (Math.random() > 0.3 ? speed : -speed);
            y += (Math.random() > 0.3 ? speed : -speed);
        }
    }

    public void decideNextMove() {
        if (state == EnemyState.FRIGHTENED) {
            x += (Math.random() > 0.3 ? -speed : speed);
            y += (Math.random() > 0.3 ? -speed : speed);
        }
    }

    public boolean isCollidingWithWall(List<Wall> walls) {
        Rectangle ghostBounds = new Rectangle(x, y, 20, 20);
        
        switch (state) {
            case CHASE:
                ghostBounds.x += (target.getX() > x) ? speed : -speed;
                ghostBounds.y += (target.getY() > y) ? speed : -speed;
                break;
            case SCATTER:
                ghostBounds.x += (Math.random() > 0.2 ? speed : -speed);
                ghostBounds.y += (Math.random() > 0.2 ? speed : -speed);
                break;
            case FRIGHTENED:
                ghostBounds.x += (Math.random() > 0.2 ? -speed : speed);
                ghostBounds.y += (Math.random() > 0.2 ? -speed : speed);
                break;
        }
        
        for (Wall wall : walls) {
            if (ghostBounds.intersects(wall.getBounds())) {
                return true;
            }
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
