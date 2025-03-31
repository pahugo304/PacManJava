package main.java.fr.ynov.pacman.domain.maze;

import main.java.fr.ynov.pacman.domain.entity.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

// Represents the game map, including walls, coins, and super coins
public class Maze {
    private final List<Wall> walls;
    private final List<Coin> coins;
    private final List<SuperCoin> superCoins;
    private final int width;
    private final int height;
    private final int cellSize;

    public Maze(int width, int height, int cellSize) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.walls = new ArrayList<>();
        this.coins = new ArrayList<>();
        this.superCoins = new ArrayList<>();
        initialize();
    }

    // Initializes the map elements
    private void initialize() {
        createBorders();
        createInnerWalls();
        distributeCoins();
        placeSuperCoins();
    }

    // Creates the outer border walls
    private void createBorders() {
        for (int x = 0; x < width; x += cellSize) {
            walls.add(new Wall(x, 0, cellSize, cellSize));
            walls.add(new Wall(x, height - cellSize, cellSize, cellSize));
        }

        for (int y = cellSize; y < height - cellSize; y += cellSize) {
            walls.add(new Wall(0, y, cellSize, cellSize));
            walls.add(new Wall(width - cellSize, y, cellSize, cellSize));
        }
    }

    // Places some inner walls manually
    private void createInnerWalls() {
        walls.add(new Wall(100, 100, 150, cellSize));
        walls.add(new Wall(300, 200, 150, cellSize));
        walls.add(new Wall(200, 150, cellSize, 100));
        walls.add(new Wall(400, 250, cellSize, 100));
    }

    // Distributes small coins across the map grid
    private void distributeCoins() {
        for (int x = cellSize * 2; x < width - cellSize * 2; x += cellSize) {
            for (int y = cellSize * 2; y < height - cellSize * 2; y += cellSize) {
                if (!isWallAtPosition(x, y)) {
                    coins.add(new Coin(x + cellSize / 4, y + cellSize / 4));
                }
            }
        }
    }

    // Places four Super Coins in map corners
    private void placeSuperCoins() {
        int[] positionsX = {cellSize * 3, width - cellSize * 4, cellSize * 3, width - cellSize * 4};
        int[] positionsY = {cellSize * 3, cellSize * 3, height - cellSize * 4, height - cellSize * 4};

        for (int i = 0; i < positionsX.length; i++) {
            if (!isWallAtPosition(positionsX[i], positionsY[i])) {
                superCoins.add(new SuperCoin(positionsX[i], positionsY[i]));
            }
        }
    }

    // Checks if a wall overlaps a given position
    private boolean isWallAtPosition(int x, int y) {
        for (Wall wall : walls) {
            if (x >= wall.x &&
                x < wall.x + wall.width &&
                y >= wall.y &&
                y < wall.y + wall.height) {
                return true;
            }
        }
        return false;
    }

    // Public getters and utility methods
    public List<Wall> getWalls() {
        return new ArrayList<>(walls);
    }

    public List<Coin> getCoins() {
        return new ArrayList<>(coins);
    }

    public List<SuperCoin> getSuperCoins() {
        return new ArrayList<>(superCoins);
    }

    public void removeCoin(Coin coin) {
        coins.remove(coin);
    }

    public void removeSuperCoin(SuperCoin superCoin) {
        superCoins.remove(superCoin);
    }

    // Renders all maze elements
    public void draw(Graphics g) {
        for (Wall wall : walls) {
            wall.draw(g);
        }

        for (Coin coin : coins) {
            coin.draw(g);
        }

        for (SuperCoin superCoin : superCoins) {
            superCoin.draw(g);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
