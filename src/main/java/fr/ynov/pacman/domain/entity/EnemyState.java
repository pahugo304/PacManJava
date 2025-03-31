package main.java.fr.ynov.pacman.domain.entity;

// Defines possible AI states for an enemy
public enum EnemyState {
    CHASE,       // Follows Pac-Man
    SCATTER,     // Moves to corners
    FRIGHTENED   // Can be eaten by Pac-Man
}
