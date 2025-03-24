package main.java.fr.ynov.pacman.domain.game;

import main.java.fr.ynov.pacman.domain.entity.*;
import main.java.fr.ynov.pacman.domain.maze.Maze;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.KeyEvent;

public class Game {
    private Pacman player;
    private List<Ghost> ghosts;
    private Maze maze;
    private GameState state;
    private int score;
    private int lives;
    private boolean superCoinActive;
    private int superCoinTimer;
    private static final int FRIGHTENED_DURATION = 300;
    private static final int CELL_SIZE = 20;

    public Game(int width, int height) {
        this.maze = new Maze(width, height, CELL_SIZE);
        this.player = new Pacman(width/2, height/2, 2);
        this.ghosts = initializeGhosts();
        this.state = GameState.PLAYING;
        this.score = 0;
        this.lives = 3;
        this.superCoinActive = false;
    }

    private List<Ghost> initializeGhosts() {
        List<Ghost> ghosts = new ArrayList<>();
        ghosts.add(new Ghost(100, 100, 1, Color.RED, player));
        ghosts.add(new Ghost(500, 100, 1, Color.PINK, player));
        ghosts.add(new Ghost(100, 500, 1, Color.CYAN, player));
        ghosts.add(new Ghost(500, 500, 1, Color.ORANGE, player));
        return ghosts;
    }

    public void update() {
        if (state != GameState.PLAYING) return;
        player.move();
        checkWallCollisions();
        checkCoinCollisions();
        checkSuperCoinCollisions();

        ghosts.forEach(ghost -> {
            ghost.move();
            checkGhostCollision(ghost);
        });
        if (superCoinActive && --superCoinTimer <= 0) {
            endSuperCoinEffect();
        }
        checkGameStatus();
    }

    private void checkWallCollisions() {
        for (Wall wall : maze.getWalls()) {
            if (player.isCollidingWithWall(wall)) {
                player.setDirection(Direction.NONE);
                break;
            }
        }
    }

    private void checkCoinCollisions() {
        Iterator<Coin> iterator = maze.getCoins().iterator();
        while (iterator.hasNext()) {
            Coin coin = iterator.next();
            if (player.isColliding(coin)) {
                score += 10;
                iterator.remove();
            }
        }
    }

    private void checkSuperCoinCollisions() {
        maze.getSuperCoins().removeIf(superCoin -> {
            if (player.isColliding(superCoin)) {
                score += 50;
                activateSuperCoinEffect();
                return true;
            }
            return false;
        });
    }

    private void activateSuperCoinEffect() {
        superCoinActive = true;
        superCoinTimer = FRIGHTENED_DURATION;
        ghosts.forEach(ghost -> ghost.changeState(EnemyState.FRIGHTENED));
    }

    private void endSuperCoinEffect() {
        superCoinActive = false;
        ghosts.forEach(ghost -> ghost.changeState(EnemyState.CHASE));
    }

    private void checkGhostCollision(Ghost ghost) {
        if (player.isColliding(ghost)) {
            if (ghost.getState() == EnemyState.FRIGHTENED) {
                ghost.die();
                score += 200;
            } else {
                loseLife();
            }
        }
    }

    private void loseLife() {
        if (--lives <= 0) {
            state = GameState.GAME_OVER;
        } else {
            resetPositions();
        }
    }

    private void resetPositions() {
        player.setX(maze.getWidth()/2);
        player.setY(maze.getHeight()/2);
        player.setDirection(Direction.NONE);
        
        ghosts.get(0).setX(100); ghosts.get(0).setY(100);
        ghosts.get(1).setX(500); ghosts.get(1).setY(100);
    }

    private void checkGameStatus() {
        if (maze.getCoins().isEmpty() && maze.getSuperCoins().isEmpty()) {
            state = GameState.VICTORY;
        }
    }

    public void draw(Graphics g) {
        maze.draw(g);
        player.draw(g);
        ghosts.forEach(ghost -> ghost.draw(g));
        
        drawHUD(g);
    }

    private void drawHUD(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 20, 20);
        g.drawString("Lives: " + lives, 20, 40);
        
        if (superCoinActive) {
            g.drawString("POWER: " + (superCoinTimer/60 + 1), 20, 60);
        }
    }

    public void handleInput(int keyCode) {
        if (state == GameState.PLAYING) {
            player.handleKeyPress(keyCode);
        } else if (state == GameState.GAME_OVER || state == GameState.VICTORY) {
            if (keyCode == KeyEvent.VK_ENTER) {
                resetGame();
            }
        }
    }

    private void resetGame() {
        this.maze = new Maze(maze.getWidth(), maze.getHeight(), CELL_SIZE);
        this.player = new Pacman(maze.getWidth()/2, maze.getHeight()/2, 2);
        this.ghosts = initializeGhosts();
        this.state = GameState.PLAYING;
        this.score = 0;
        this.lives = 3;
        this.superCoinActive = false;
    }

    public GameState getState() { return state; }
    public int getScore() { return score; }
    public int getLives() { return lives; }
}
