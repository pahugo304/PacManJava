package main.java.fr.ynov.pacman.domain.game;

// Represents the current state of the game
public enum GameState {
    MENU,        // On the start menu
    PLAYING,     // Actively running
    PAUSED,      // Temporarily stopped
    GAME_OVER,   // Player lost all lives
    VICTORY      // Player completed the game
}
