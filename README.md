#  Pac-Man Clone (Java Swing)

A modern recreation of the classic **Pac-Man** game written in **Java** using **Swing** for the GUI. This project includes moving ghosts with AI states, animated player movement, coins, walls, and game state management.

---

##  Features

- Arrow key movement for Pac-Man
- Animated mouth and eye
- Ghosts with different AI states:
  - `CHASE`, `SCATTER`, and `FRIGHTENED`
- Super coins that make ghosts vulnerable
- Lives, score, victory and game over conditions
- Custom maze with walls, borders, and collectibles

---

##  Class Diagram

[View the interactive class diagram](https://raw.githubusercontent.com/pahugo304/PacManJava/refs/heads/main/pacman.drawio.html)

---

##  Requirements

- Java 17 or later
- IDE like IntelliJ or Eclipse (optional, but recommended)
- Alternatively: terminal with `javac` and `java`

---

##  How to Run

###  Compile & Run from Terminal

```bash
# Navigate to the source folder
cd src

# Compile all Java classes
javac main/java/fr/ynov/pacman/**/*.java

# Run the main class
java main.java.fr.ynov.pacman.main.Main
